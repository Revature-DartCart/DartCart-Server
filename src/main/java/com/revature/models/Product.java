package com.revature.models;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * This class represents an individual Product.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private int id;

    private String name;

    @Column(length = 1000)
    private String description;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="Product_Category", joinColumns = @JoinColumn(name="shop_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}