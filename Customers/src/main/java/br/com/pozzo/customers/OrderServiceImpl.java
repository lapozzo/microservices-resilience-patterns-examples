package br.com.pozzo.customers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Service
public class OrderServiceImpl implements OrderService {
	Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private final RestTemplate restTemplate = null;

	@SuppressWarnings("unchecked")
	@Override
	@Retry(name = "getOrdersWithRetry")
	public List<Order> getOrdersWithRetry(int customerId) {
		logger.info(String.format("Calling order service with retry for customer %d", customerId));
		return restTemplate.getForObject("http://localhost:9090/orders?customer=" + customerId, List.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	@CircuitBreaker(name = "getOrdersWithCircuit", fallbackMethod = "getCachedOrders")
	public List<Order> getOrdersWithCircuit(int customerId) {
		logger.info(String.format("Calling order service with circuit breaker for customer %d", customerId));
		return restTemplate.getForObject("http://localhost:9090/orders?customer=" + customerId, List.class);
	}

	public List<Order> getCachedOrders(int customerId, Throwable ex) {
		logger.info(String.format("Fallback - Retriving orders from cache for customer %d", customerId));
		List<Order> cachedOrders = new ArrayList<>(2);
		cachedOrders.add(new Order(1, 50d));
		cachedOrders.add(new Order(2, 120d));
		return cachedOrders;
	}
}
