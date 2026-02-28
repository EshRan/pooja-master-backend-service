package com.rituals.basket.pooja.market.data.service.core.controller;

import com.rituals.basket.pooja.market.data.service.core.dto.OrderRequestDto;
import com.rituals.basket.pooja.market.data.service.core.service.OrderNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderNotificationService orderNotificationService;

    @Autowired
    public OrderController(OrderNotificationService orderNotificationService) {
        this.orderNotificationService = orderNotificationService;
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto orderRequest) {
        try {
            // Future feature: Save order to database here

            // Send WhatsApp notification
            orderNotificationService.processOrderToWhatsApp(orderRequest);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("{\"success\": true, \"message\": \"Order placed successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"success\": false, \"message\": \"Failed to process order\"}");
        }
    }
}
