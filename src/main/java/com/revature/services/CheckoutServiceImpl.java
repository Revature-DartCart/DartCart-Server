package com.revature.services;

import com.revature.exceptions.BadTransactionException;
import com.revature.models.*;
import com.revature.repositories.CartItemRepository;
import com.revature.repositories.InvoiceRepository;
import com.revature.repositories.ShopProductRepo;
import com.revature.repositories.UserRepo;
import java.util.*;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ShopProductRepo shopProductRepo;

    /**
     * This method receives a User with a full cart of items and compares
     * that cart with the ones saved in the database. If there is a mismatch,
     * we return the User with the saved cart to the frontend. If there is not
     * a mismatch, we perform the checkout process (create an invoice, empty cart).
     * This is transactional, so if something goes wrong we can rollback any database
     * changes.
     *
     * @param user        The user who is checking out
     * @return            User object that has a corrected cart
     */
    @Override
    @Transactional
    public User checkout(User user) throws BadTransactionException {
        Optional<User> op = userRepo.findById(user.getId());

        if (op.isPresent()) {
            User fetchedUser = op.get();

            // First check if the posted user has the same data as the database
            if (user.getItemList().size() == fetchedUser.getItemList().size()) {
                Set<Integer> clientCartItemIds = new HashSet<>();
                user.getItemList().forEach(cartItem -> clientCartItemIds.add(cartItem.getId()));

                if (
                    fetchedUser
                        .getItemList()
                        .stream()
                        .allMatch(cartItem -> clientCartItemIds.contains(cartItem.getId()))
                ) {
                    // begin checkout process
                    long date = System.currentTimeMillis();

                    // build a map of shop ids to an invoice
                    // so each shop has an invoice for an order
                    Map<Integer, Invoice> invoiceMap = new HashMap<>();

                    // This map is used to keep track of updating the quantities in shopproduct
                    Map<Integer, Integer> shopProductModifications = new HashMap<>();
                    for (CartItem item : fetchedUser.getItemList()) {
                        // If we try to purchase more than what's in stock we throw an exception
                        if (item.getQuantity() > item.getShopProduct().getQuantity()) {
                            throw new BadTransactionException();
                        }

                        int shopId = item.getShopProduct().getShop().getId();

                        if (!invoiceMap.containsKey(shopId)) {
                            // create invoice for shop
                            Invoice invoice = new Invoice();
                            invoice.setCustomer(fetchedUser);
                            invoice.setOrderPlaced(date);
                            invoice.setShop(item.getShopProduct().getShop());
                            invoice.setShippedFrom(item.getShopProduct().getShop().getLocation());
                            invoice.setShippedTo(user.getLocation());
                            invoice.setOrderDetails(new ArrayList<>());

                            // Create order details for this cart item
                            OrderDetail orderDetail = new OrderDetail();
                            orderDetail.setName(item.getShopProduct().getProduct().getName());
                            orderDetail.setDescription(item.getShopProduct().getProduct().getName());
                            orderDetail.setQuantity(item.getQuantity());
                            orderDetail.setCost(item.getShopProduct().getPrice());
                            shopProductModifications.put(item.getShopProduct().getId(), item.getQuantity());

                            invoice.getOrderDetails().add(orderDetail);

                            invoiceMap.put(shopId, invoice);
                        } else {
                            // add detail for invoice
                            OrderDetail orderDetail = new OrderDetail();
                            orderDetail.setName(item.getShopProduct().getProduct().getName());
                            orderDetail.setDescription(item.getShopProduct().getProduct().getName());
                            orderDetail.setQuantity(item.getQuantity());
                            orderDetail.setCost(item.getShopProduct().getPrice());
                            shopProductModifications.put(item.getShopProduct().getId(), item.getQuantity());

                            invoiceMap.get(shopId).getOrderDetails().add(orderDetail);
                        }
                    }

                    // Here we save the invoices
                    invoiceMap.values().forEach(invoice -> invoiceRepository.save(invoice));

                    // Update quantity on each shopproduct
                    List<ShopProduct> products = (List<ShopProduct>) shopProductRepo.findAllById(
                        shopProductModifications.keySet()
                    );
                    products.forEach(
                        shopProduct -> {
                            shopProduct.setQuantity(
                                shopProduct.getQuantity() - shopProductModifications.get(shopProduct.getId())
                            );
                        }
                    );
                    shopProductRepo.saveAll(products);

                    cartItemRepository.deleteAll();

                    fetchedUser.getItemList().clear();

                    return fetchedUser;
                }
            }

            // Need to resolve all lazy loading in user dependencies
            User resolvedUser = new User();
            resolvedUser.setId(fetchedUser.getId());
            resolvedUser.setUsername(fetchedUser.getUsername());
            resolvedUser.setPassword(fetchedUser.getPassword());
            resolvedUser.setPhone(fetchedUser.getPhone());
            resolvedUser.setEmail(fetchedUser.getEmail());
            resolvedUser.setFirstName(fetchedUser.getFirstName());
            resolvedUser.setLastName(fetchedUser.getLastName());
            resolvedUser.setRegistrationDate(fetchedUser.getRegistrationDate());
            resolvedUser.setLocation(fetchedUser.getLocation());

            resolvedUser.setItemList(new ArrayList<>());
            fetchedUser
                .getItemList()
                .forEach(
                    cartItem -> {
                        ShopProduct shopProduct = new ShopProduct();
                        shopProduct.setShop(cartItem.getShopProduct().getShop());
                        shopProduct.setId(cartItem.getShopProduct().getId());
                        shopProduct.setPrice(cartItem.getShopProduct().getPrice());
                        shopProduct.setQuantity(cartItem.getShopProduct().getQuantity());
                        shopProduct.setDiscount(cartItem.getShopProduct().getDiscount());

                        Product product = new Product();
                        product.setId(cartItem.getShopProduct().getProduct().getId());
                        product.setName(cartItem.getShopProduct().getProduct().getName());
                        product.setDescription(cartItem.getShopProduct().getProduct().getDescription());
                        product.setCategories(new ArrayList<>());
                        cartItem
                            .getShopProduct()
                            .getProduct()
                            .getCategories()
                            .forEach(category -> product.getCategories().add(category));
                        shopProduct.setProduct(product);

                        cartItem.setShopProduct(shopProduct);
                        cartItem.setCustomer(null);
                        resolvedUser.getItemList().add(cartItem);
                    }
                );

            return fetchedUser; // incoming user data didn't match database
        } else {
            return user; // database couldn't find user
        }
    }
}
