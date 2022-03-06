package com.revature.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents an individual item in the cart or saved by a Customer.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CartItems")
public class CartItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cart_item_id")
  private int id;

  private int quantity;

  // if saved, set to wishlist, if not saved is in cart
  private boolean saved;

  @ManyToOne
  @JoinColumn(name = "customer_id")
  private User customer;

  @OneToOne
  private ShopProduct shopProduct;
}
