package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.revature.models.Seller;

import javax.persistence.*;

/**
 * This class represents an individual location controlled by a Seller.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Shops")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shop_id")
    private int id;

    @NotNull
    private String location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    public Shop(int id){
        this.id = id;
        location = "";
        seller = new Seller();
    }

}