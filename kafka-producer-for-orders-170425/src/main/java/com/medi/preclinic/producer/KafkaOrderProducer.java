package com.medi.preclinic.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.medi.preclinic.event.OrderPlacedEvent;

@Service
public class KafkaOrderProducer {
	private static final String TOPIC = "order.placed";
	
	@Autowired
	private KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;
	
	public void send(OrderPlacedEvent orderPlacedEvent) {
		kafkaTemplate.send(TOPIC, orderPlacedEvent);
	}
}
