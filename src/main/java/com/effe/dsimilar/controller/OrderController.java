package com.effe.dsimilar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.effe.dsimilar.exception.OrderException;
import com.effe.dsimilar.exception.UserException;
import com.effe.dsimilar.model.Address;
import com.effe.dsimilar.model.Order;
import com.effe.dsimilar.model.User;
import com.effe.dsimilar.service.OrderService;
import com.effe.dsimilar.service.UserService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderService orderService;

	@PostMapping("/")
	public ResponseEntity<Order> createOrder(@RequestBody Address shippingAddress,
			@RequestHeader("Authorization") String jwt) throws UserException {

		User user = userService.findUserProfileByJwt(jwt);

		Order order = orderService.createOrder(user, shippingAddress);

		System.out.println("Order" + order);

		return new ResponseEntity<Order>(order, HttpStatus.CREATED);
	}

	@GetMapping("/user")
	public ResponseEntity<List<Order>> userOrderHistory(@RequestHeader("Authorization") String jwt)
			throws UserException {

		User user = userService.findUserProfileByJwt(jwt);

		List<Order> order = orderService.usersOrderHistory(user.getId());

		return new ResponseEntity<>(order, HttpStatus.CREATED);
	}

	@GetMapping("/{Id}")
	public ResponseEntity<Order> findOrderById(@PathVariable("Id") Long orderId,
			@RequestHeader("Authorization") String jwt) throws UserException, OrderException {

		User user = userService.findUserProfileByJwt(jwt);

		Order order = orderService.findOrderById(orderId);

		return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
	}
}
