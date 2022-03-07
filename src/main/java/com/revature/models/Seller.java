package com.revature.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * This class represents a Seller entity in the database.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seller_id")
    private int id;

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    private String homepage;

    private String description;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


}
