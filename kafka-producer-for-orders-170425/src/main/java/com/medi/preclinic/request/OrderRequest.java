package com.medi.preclinic.request;

import lombok.Data;

@Data
public class OrderRequest {
	private String product;
	private int quantity;
}
