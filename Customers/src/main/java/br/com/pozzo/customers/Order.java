package br.com.pozzo.customers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
	private int orderId;
	private double orderTotal;

	public Order() {
	}

	public Order(int orderId, double orderTotal) {
		super();
		this.orderId = orderId;
		this.orderTotal = orderTotal;
	}
}
