package com.naipunya.ecommerce.order;

import com.naipunya.ecommerce.customer.CustomerClient;
import com.naipunya.ecommerce.exception.BusinessException;
import com.naipunya.ecommerce.kafka.OrderConfirmation;
import com.naipunya.ecommerce.kafka.OrderProducer;
import com.naipunya.ecommerce.orderline.OrderLineRequest;
import com.naipunya.ecommerce.orderline.OrderLineService;
import com.naipunya.ecommerce.payment.PaymentClient;
import com.naipunya.ecommerce.payment.PaymentRequest;
import com.naipunya.ecommerce.product.ProductClient;
import com.naipunya.ecommerce.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Integer createOrder(OrderRequest orderRequest){

        //check the customer --> openfeign(feignclient)
        var customer = this.customerClient.findCustomerById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        //purchase the products --> product-services(resttemplate)
        var purchasedProducts = productClient.purchaseProducts(orderRequest.products());

        //persist the order
        var order = orderRepository.save(orderMapper.toOrder(orderRequest));

        //persist order lines
        for(PurchaseRequest purchaseRequest : orderRequest.products()){
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
        //start payment process
        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        //send the order confirmation --> notification-service(kafka)
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAllOrders(){
        return this.orderRepository.findAll()
                .stream()
                .map(this.orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id){
        return this.orderRepository.findById(id)
                .map(this.orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
