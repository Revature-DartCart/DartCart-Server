package com.revature.services;

import com.revature.models.*;
import com.revature.repositories.ProductRepo;
import com.revature.repositories.ShopProductRepo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopProductServiceImpl implements ShopProductService {
  @Autowired
  ShopProductRepo shopProductRepo;

  @Autowired
  ProductRepo productRepo;

  @Autowired
  ProductRepo pr;

  @Override
  public List<ShopProduct> getAllShopProducts() {
    return (List<ShopProduct>) shopProductRepo.findAll();
  }

  @Override
  public Optional<ShopProduct> getShopProductById(int id) {
    return shopProductRepo.findById(id);
  }

  @Override
  public List<ShopProduct> searchByProductName(String searchString) {
    List<ShopProduct> shopProductList = (List<ShopProduct>) shopProductRepo.findAll();
    return shopProductList
      .stream()
      .filter(
        shopProduct ->
          shopProduct
            .getProduct()
            .getName()
            .toLowerCase()
            .contains(searchString.toLowerCase())
      )
      .collect(Collectors.toList());
  }

  @Override
  public List<Product> getByProductCategory(String name, String category) {
    List<Product> productList = (List<Product>) productRepo.findAll();
    if (name != null) {
      productList =
        productList
          .stream()
          .filter(
            product ->
              product.getName().toLowerCase().contains(name.toLowerCase()) ||
              product
                .getDescription()
                .toLowerCase()
                .contains(name.toLowerCase())
          )
          .collect(Collectors.toList());
    }
    if (category != null) {
      productList =
        productList
          .stream()
          .filter(
            product -> {
              List<Category> categories = product.getCategories();
              for (Category category1 : categories) {
                if (category1.getName().equalsIgnoreCase(category)) return true;
              }
              return false;
            }
          )
          .collect(Collectors.toList());
    }
    return productList;
  }

  @Override
  public List<ShopProductResponse> getSellersForProduct(int id) {
    List<ShopProduct> list = (List<ShopProduct>) shopProductRepo.findAll();
    ArrayList<ShopProductResponse> shopProducts = new ArrayList<>();

    list.stream().forEach(shopProduct -> {
              if (shopProduct.getProduct().getId() == id) {

          shopProducts.add(new ShopProductResponse(
                        shopProduct.getId(),
                        shopProduct.getShop(),
                        shopProduct.getProduct(),
                        shopProduct.getPrice(),
                        shopProduct.getShop().getLocation(),
                        shopProduct.getDiscount(),
                        shopProduct.getQuantity(),
                        shopProduct.getShop().getSeller().getDescription()
                ));
              }
            }
    );

    return shopProducts;
  }
}
