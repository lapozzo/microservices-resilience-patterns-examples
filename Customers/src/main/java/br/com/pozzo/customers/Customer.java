package br.com.pozzo.customers;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
	private int id;
	private String name;
	private List<Order> orders;

	public Customer(int id, String name, List<Order> orders) {
		super();
		this.id = id;
		this.name = name;
		this.orders = orders;
	}
}
