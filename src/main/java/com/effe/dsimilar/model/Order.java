package com.effe.dsimilar.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orderBy")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "order_id")
	private String orderId;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderitems = new ArrayList<>();

	private LocalDateTime orderDate;

	private LocalDateTime deliverDate;

	@OneToOne
	private Address shippingAddress;

	@Embedded
	private PaymentDetails paymentdetails = new PaymentDetails();

	private double totalPrice;

	private int totalDiscountedPrice;

	private int discount;

	private String orderStatus;

	private int totalItem;

	private LocalDateTime createdAt;

	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(Long id, String orderId, User user, List<OrderItem> orderitems, LocalDateTime orderDate,
			LocalDateTime deliverDate, Address shippingAddress, PaymentDetails paymentdetails, double totalPrice,
			int totalDiscountedPrice, int discount, String orderStatus, int totalItem, LocalDateTime createAt) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.user = user;
		this.orderitems = orderitems;
		this.orderDate = orderDate;
		this.deliverDate = deliverDate;
		this.shippingAddress = shippingAddress;
		this.paymentdetails = paymentdetails;
		this.totalPrice = totalPrice;
		this.totalDiscountedPrice = totalDiscountedPrice;
		this.discount = discount;
		this.orderStatus = orderStatus;
		this.totalItem = totalItem;
		this.createdAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderItem> getOrderitems() {
		return orderitems;
	}

	public void setOrderitems(List<OrderItem> orderitems) {
		this.orderitems = orderitems;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getDeliverDate() {
		return deliverDate;
	}

	public void setDeliverDate(LocalDateTime deliverDate) {
		this.deliverDate = deliverDate;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public PaymentDetails getPaymentdetails() {
		return paymentdetails;
	}

	public void setPaymentdetails(PaymentDetails paymentdetails) {
		this.paymentdetails = paymentdetails;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalDiscountedPrice() {
		return totalDiscountedPrice;
	}

	public void setTotalDiscountedPrice(int totalDiscountedPrice) {
		this.totalDiscountedPrice = totalDiscountedPrice;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public LocalDateTime getCreateAt() {
		return createdAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createdAt = createAt;
	}

}
