package com.naipunya.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.naipunya.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {
}
