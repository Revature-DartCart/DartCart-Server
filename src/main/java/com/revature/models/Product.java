package com.revature.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    private String name;

    @Column(length = 1000)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="Product_Category", joinColumns = @JoinColumn(name="product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

}