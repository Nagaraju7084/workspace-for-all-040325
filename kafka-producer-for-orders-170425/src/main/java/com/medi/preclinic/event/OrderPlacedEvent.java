package com.medi.preclinic.event;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderPlacedEvent {
	private String orderId;
	private String customerId;
	private LocalDateTime createdAt;
	
	public OrderPlacedEvent(String orderId, String customerId) {
		this.orderId = orderId;
		this.customerId = customerId;
		this.createdAt = LocalDateTime.now();
	}

}
