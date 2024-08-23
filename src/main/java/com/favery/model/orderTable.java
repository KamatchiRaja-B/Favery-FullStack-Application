package com.favery.model;

public class orderTable {
	
	private int orderId;
	private int restaurantId;
	private int userId;
	private Float totalAmount;
	private String status;
	private String paymentMode;

	public orderTable() {
		super();
	}

	public orderTable(int orderId, int restaurantId, int userId, Float totalAmount, String status, String paymentMode) {
		super();
		this.orderId = orderId;
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}

	public orderTable(int restaurantId, int userId, Float totalAmount, String status, String paymentMode) {
		super();
		this.restaurantId = restaurantId;
		this.userId = userId;
		this.totalAmount = totalAmount;
		this.status = status;
		this.paymentMode = paymentMode;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	@Override
	public String toString() {
		return "orderTable [orderId=" + orderId + ", restaurantId=" + restaurantId + ", userId=" + userId
				+ ", totalAmount=" + totalAmount + ", status=" + status + ", paymentMode=" + paymentMode + "]";
	}

}
