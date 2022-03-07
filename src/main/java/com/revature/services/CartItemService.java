package com.revature.services;
import com.revature.models.CartItem;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface CartItemService {

    public List<CartItem> getAllCartItem();
    public List <CartItem> getAllCartItem(int userId);
    public CartItem addCartItem(CartItem cartItem);
    public CartItem updateCartItem(CartItem change);
    public CartItem getCurrentCartbyId(int userId);
    public CartItem getbyId(int id);
    public boolean deleteById(int id);
}
