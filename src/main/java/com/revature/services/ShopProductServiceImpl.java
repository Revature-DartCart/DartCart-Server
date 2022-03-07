package com.revature.services;

import com.revature.models.*;
import com.revature.repositories.ProductRepo;
import com.revature.repositories.ShopProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopProductServiceImpl implements ShopProductService {
    @Autowired
    ShopProductRepo shopProductRepository;

    @Autowired
    ProductRepo pr;

    @Override
    public List<ShopProduct> getAllShopProducts() {
        return (List<ShopProduct>) shopProductRepository.findAll();
    }

    @Override
    public Optional<ShopProduct> getShopProductById(int id) {

        return shopProductRepository.findById(id);
    }

    @Override
    public List<ShopProductResponse> getSellersForProduct(int id) {
        List<ShopProduct> allListings =  shopProductRepository.findByProduct(shopProductRepository.findById(id).get().getProduct());

        ArrayList<ShopProductResponse> shopProducts = new ArrayList<>();

        for(ShopProduct s:allListings)
        {
            shopProducts.add(new ShopProductResponse(s.getShop_product_id()
                    ,s.getShop().getId()
                    ,s.getProduct()
                    ,s.getPrice()
                    ,s.getShop().getLocation()
                    ,s.getDiscount()
                    ,s.getQuantity()
                    ,s.getShop().getSeller().getDescription()));
        }
        return shopProducts;
    }
}
