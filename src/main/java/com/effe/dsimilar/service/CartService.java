package com.effe.dsimilar.service;

import com.effe.dsimilar.exception.ProductException;
import com.effe.dsimilar.model.Cart;
import com.effe.dsimilar.model.User;
import com.effe.dsimilar.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId,AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);
}
