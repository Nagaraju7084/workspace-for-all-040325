package com.medi.preclinic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medi.preclinic.entity.Order;
import com.medi.preclinic.event.OrderPlacedEvent;
import com.medi.preclinic.producer.KafkaEventProducer;
import com.medi.preclinic.producer.KafkaOrderProducer;
import com.medi.preclinic.repository.OrderRepository;
import com.medi.preclinic.request.OrderRequest;

@RestController
@RequestMapping("/api/orders")
public class KafkaOrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private KafkaEventProducer kafkaEventProducer;
	
	@Autowired
	private KafkaOrderProducer kafkaOrderProducer;
	
	@PostMapping
	public String placeOrder(@RequestBody OrderRequest orderRequest) {
		Order order = new Order();
		order.setProduct(orderRequest.getProduct());
		order.setQuantity(orderRequest.getQuantity());
		orderRepository.save(order);
		
		OrderPlacedEvent event = new OrderPlacedEvent(order.getOrderId(), "cust-001");
		kafkaEventProducer.publishOrderEvent(event); //send to kafka
		
		return "order placed successfully with id: " + order.getOrderId();
	}
}
