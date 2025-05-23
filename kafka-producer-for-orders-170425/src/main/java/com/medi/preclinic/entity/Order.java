package com.medi.preclinic.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {
	
	@Id
	private String orderId;
	private String product;
	private int quantity;
	
	public Order() {
		this.orderId = UUID.randomUUID().toString();
	}
}
