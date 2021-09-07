package br.com.pozzo.orders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {
	private static final Map<Integer, List<Order>> customerOrders = new HashMap<>();
	private static final List<Order> allOrders = new ArrayList<>();

	public OrdersController() {
		if (customerOrders.size() == 0) {
			generateOrders();
		}
	}

	@RequestMapping("/orders")
	public ResponseEntity<List<Order>> get() {
		return new ResponseEntity<List<Order>>(allOrders, HttpStatus.OK);
	}

	@RequestMapping(value = "/orders", params = "customer")
	public ResponseEntity<List<Order>> get(@RequestParam("customer") int customerId) throws InterruptedException {
		Thread.sleep(1 + (long) (Math.random() * 500));
		if (Math.random() > 0.8) {
			throw new RuntimeException("Unexpected Error!");
		}
		if (!customerOrders.containsKey(customerId)) {
			return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Order>>(customerOrders.get(customerId), HttpStatus.OK);
	}

	private void generateOrders() {
		int maxCustomerId = 50;
		for (int currCustomerId = 1; currCustomerId <= maxCustomerId; currCustomerId++) {
			int maxOrders = 10;
			List<Order> orders = new ArrayList<>(maxOrders);
			for (int currOrderId = 1; currOrderId <= maxOrders; currOrderId++) {
				double totalFactor = new Random().nextDouble() * 10;
				double orderTotal = totalFactor * currOrderId;
				orders.add(new Order(currCustomerId, currOrderId, orderTotal));
			}
			allOrders.addAll(orders);
			customerOrders.put(currCustomerId, orders);
		}
	}
}