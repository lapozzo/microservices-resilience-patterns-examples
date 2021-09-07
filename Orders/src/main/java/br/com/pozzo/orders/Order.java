package br.com.pozzo.orders;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
	private int id;
	private double total;
	private Date date;
	private int customerId;

	public Order(int customerId, int id, double total) {
		super();
		this.customerId = customerId;
		this.id = id;
		this.total = total;
		this.date = new Date();
	}
}
