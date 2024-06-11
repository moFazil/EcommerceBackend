package com.effe.dsimilar.service;

import com.effe.dsimilar.exception.CartItemException;
import com.effe.dsimilar.exception.UserException;
import com.effe.dsimilar.model.Cart;
import com.effe.dsimilar.model.CartItem;
import com.effe.dsimilar.model.Product;

public interface CartItemService {
	
	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId,Long id,CartItem cartItem) throws CartItemException,UserException;
	
	public CartItem isCartItemExist(Cart cart,Product product,String size,Long userId);
	
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException,UserException;
	
	public CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
