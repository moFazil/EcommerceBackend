package com.effe.dsimilar.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.effe.dsimilar.exception.OrderException;
import com.effe.dsimilar.model.Address;
import com.effe.dsimilar.model.Order;
import com.effe.dsimilar.model.User;

@Service
public interface OrderService {

	public Order createOrder(User user, Address shippingAddress);

	public Order findOrderById(Long orderId) throws OrderException;

	public List<Order> usersOrderHistory(Long userId);

	public Order placedOrder(Long orderId) throws OrderException;

	public Order confirmedOrder(Long orderId) throws OrderException;

	public Order shippedOrder(Long orderId) throws OrderException;

	public Order deliveredOrder(Long orderId) throws OrderException;

	public Order cancelledOrder(Long orderId) throws OrderException;

	public List<Order> getAllOrders();

	public void deleteOrder(Long OrderId) throws OrderException;

}
