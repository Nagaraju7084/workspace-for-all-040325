package com.medi.preclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medi.preclinic.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String> {

}
