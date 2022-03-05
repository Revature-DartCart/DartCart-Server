package com.revature.services;

import com.revature.models.CartItem;
import com.revature.models.Invoice;
import com.revature.models.OrderDetail;
import com.revature.models.User;
import com.revature.repositories.CartItemRepository;
import com.revature.repositories.InvoiceRepository;
import com.revature.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    /**
     * This method receives a User with a full cart of items and compares
     * that cart with the ones saved in the database. If there is a mismatch,
     * we return the User with the saved cart to the frontend. If there is not
     * a mismatch, we perform the checkout process (create an invoice, empty cart).
     *
     * @param user        The user who is checking out
     * @return            User object that has a corrected cart
     */
    @Override
    @Transactional
    public User checkout(User user) {
        Optional<User> op = userRepo.findById(user.getId());

        if(op.isPresent()) {
            User fetchedUser = op.get();

            // First check if the posted user has the same data as the database
            if(user.getItemList().size() == fetchedUser.getItemList().size()) {
                Set<Integer> clientCartItemIds = new HashSet<>();
                user.getItemList().forEach(cartItem -> clientCartItemIds.add(cartItem.getId()));

                if(fetchedUser.getItemList().stream().allMatch(cartItem -> clientCartItemIds.contains(cartItem.getId()))) {
                    // begin checkout process
                    long date = System.currentTimeMillis();

                    // build a map of shop ids to an invoice
                    // so each shop has an invoice for an order
                    Map<Integer, Invoice> invoiceMap = new HashMap<>();
                    for (CartItem item: fetchedUser.getItemList()) {
                        int shopId = item.getShopProduct().getShop().getId();

                        if(!invoiceMap.containsKey(shopId)) {
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

                            invoice.getOrderDetails().add(orderDetail);

                            invoiceMap.put(shopId, invoice);
                        } else {
                            // add detail for invoice
                            OrderDetail orderDetail = new OrderDetail();
                            orderDetail.setName(item.getShopProduct().getProduct().getName());
                            orderDetail.setDescription(item.getShopProduct().getProduct().getName());
                            orderDetail.setQuantity(item.getQuantity());
                            orderDetail.setCost(item.getShopProduct().getPrice());

                            invoiceMap.get(shopId).getOrderDetails().add(orderDetail);
                        }
                    }

                    for(Invoice invoice: invoiceMap.values()) {
                        invoiceRepository.save(invoice);
                    }

                    cartItemRepository.deleteAll();

                    fetchedUser.getItemList().clear();

                    return fetchedUser;
                }
            }

            return fetchedUser; // incoming user data didn't match database
        } else {
            return user; // database couldn't find user
        }
    }
}
