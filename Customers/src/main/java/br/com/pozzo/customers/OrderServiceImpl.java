package br.com.pozzo.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private final RestTemplate restTemplate = null;

	@SuppressWarnings("unchecked")
	@Retry(name = "getOrder")
	public List<Order> getOrdersWithRetry(int customerId) {
		return restTemplate.getForObject("http://localhost:9090/orders?customer=" + customerId, List.class);
	}
}
