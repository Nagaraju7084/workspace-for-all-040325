package com.naipunya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naipunya.entity.Product;

public interface InventoryRepository extends JpaRepository<Product,Integer> {
}
