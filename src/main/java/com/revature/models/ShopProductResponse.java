package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopProductResponse
{
    private int shop_product_id;
    private int shop_id;
    private Product product;
    private int price;
    private String location;
    private int discount;
    private int quantity;
    private String sellerDescription;
}
