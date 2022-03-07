package com.revature.services;

import com.revature.models.Seller;
import com.revature.models.Shop;
import com.revature.models.ShopProduct;
import com.revature.models.ShopProductResponse;

import java.util.List;
import java.util.Optional;

public interface ShopProductService {
    /**
     * This method gets all Shop Products from the database
     * @return  returns a List of all Shop Products
     */
    List<ShopProduct> getAllShopProducts();


    /**
     * Retrieves a specific ShopProduct by parsed ID
     * @param id    ID of Shop Product to be retrieved from the database
     * @return      Returns the retrieved Shop Product by the ID
     */
    Optional<ShopProduct> getShopProductById(int id);

    List<ShopProductResponse> getSellersForProduct(int id);
}
