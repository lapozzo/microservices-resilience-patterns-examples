package br.com.pozzo.customers;

import java.util.List;

public interface OrderService {
	public List<Order> getOrdersWithRetry(int customerId);
}
