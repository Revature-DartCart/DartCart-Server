package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * This class hard codes data of an individual Product placed on an invoice.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private int id;

    @Column(updatable = false)
    private int cost;
    @Column(updatable = false)
    private String name;

    @Column(length = 1000, updatable = false)
    private String description;
}