package com.medi.preclinic.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.medi.preclinic.event.OrderPlacedEvent;

@Service
public class KafkaOrderPlacedConsumer {
	private static final Logger logger = LoggerFactory.getLogger(KafkaOrderPlacedConsumer.class);
	
	 @KafkaListener(topics = "order.placed", groupId = "inventory-group")
	    public void consume(OrderPlacedEvent event) {
	        logger.info("Consumed OrderPlacedEvent: Order ID = {}, Customer ID = {}", 
	                    event.getOrderId(), event.getCustomerId());
	        // Add inventory reservation logic here
	    }
}
