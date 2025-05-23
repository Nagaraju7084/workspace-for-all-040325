package com.medi.preclinic.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.medi.preclinic.event.OrderPlacedEvent;

@Service
public class KafkaEventProducer {
	private static final Logger logger = LoggerFactory.getLogger(KafkaEventProducer.class);
	
	public void publishOrderEvent(OrderPlacedEvent orderPlacedEvent) {
		logger.info("simulated internal event publishing: {}", orderPlacedEvent.getOrderId());
	}
}
