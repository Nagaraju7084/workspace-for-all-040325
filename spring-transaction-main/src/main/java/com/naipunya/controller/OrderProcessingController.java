package com.naipunya.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naipunya.service.OrderProcessingService;
import com.naipunya.service.isolation.ReadCommittedDemo;
import com.naipunya.service.isolation.ReadUncommittedDemo;
import com.naipunya.service.isolation.RepeatableReadDemo;
import com.naipunya.service.isolation.SerializableIsolationDemo;

import com.naipunya.entity.Order;

@RestController
@RequestMapping("/api/orders")
public class OrderProcessingController {

    private final OrderProcessingService orderProcessingService;

    private final ReadUncommittedDemo readUncommittedDemo;


    private final ReadCommittedDemo readCommittedDemo;


    private final RepeatableReadDemo repeatableReadDemo;

    private final SerializableIsolationDemo serializableIsolationDemo;


    public OrderProcessingController(OrderProcessingService orderProcessingService,
                                     ReadUncommittedDemo readUncommittedDemo,
                                     ReadCommittedDemo readCommittedDemo,
                                     RepeatableReadDemo repeatableReadDemo,
                                     SerializableIsolationDemo serializableIsolationDemo) {
        this.orderProcessingService = orderProcessingService;
        this.readUncommittedDemo=readUncommittedDemo;
        this.readCommittedDemo=readCommittedDemo;
        this.repeatableReadDemo=repeatableReadDemo;
        this.serializableIsolationDemo=serializableIsolationDemo;
    }

    /**
     * API to place an order
     *
     * @param order the order details
     * @return the processed order with updated total price
     */
    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody Order order) {
        return ResponseEntity.ok(orderProcessingService.placeAnOrder(order));
    }


    @GetMapping("/isolation")
    public String testIsolation() throws InterruptedException {
        //readUncommittedDemo.testReadUncommitted(1);
        //readCommittedDemo.testReadCommitted(1);
        //repeatableReadDemo.demonstrateRepeatableRead(1);
        serializableIsolationDemo.testSerializableIsolation(1);
        return "success";
    }


}
