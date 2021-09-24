package br.com.pozzo.customers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.NotFound;

@RestController
public class CustomersController {
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/customers/with_retry/{id}")
	public ResponseEntity<Customer> getWithRetry(@PathVariable("id") int id) {
		try {
			List<Order> customerOrders = orderService.getOrdersWithRetry(id);
			return new ResponseEntity<Customer>(new Customer(id, "Customer #" + id, customerOrders), HttpStatus.OK);
		} catch (NotFound e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/customers/with_circuit/{id}")
	public ResponseEntity<Customer> getWithCircuit(@PathVariable("id") int id) {
		try {
			List<Order> customerOrders = orderService.getOrdersWithCircuit(id);
			return new ResponseEntity<Customer>(new Customer(id, "Customer #" + id, customerOrders), HttpStatus.OK);
		} catch (NotFound e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/customers/with_bulkhead/{id}")
	public ResponseEntity<Customer> getWithBulkhead(@PathVariable("id") int id) {
		try {
			List<Order> customerOrders = orderService.getOrdersWithBulkhead(id);
			return new ResponseEntity<Customer>(new Customer(id, "Customer #" + id, customerOrders), HttpStatus.OK);
		} catch (NotFound e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}		
}