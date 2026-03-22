package com.rituals.basket.pooja.market.data.service.core.service;

import com.rituals.basket.pooja.market.data.service.core.dto.OrderItemDto;
import com.rituals.basket.pooja.market.data.service.core.dto.OrderRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class OrderNotificationService {

    @Value("${twilio.account.sid}")
    private String twilioAccountSid;

    @Value("${twilio.auth.token}")
    private String twilioAuthToken;

    @Value("${twilio.from.phone}")
    private String twilioFromPhone;

    @Value("${twilio.to.phone}")
    private String twilioToPhone;

    @PostConstruct
    public void init() {
        Twilio.init(twilioAccountSid, twilioAuthToken);
        log.info("Twilio initialized with Account SID: {}", twilioAccountSid);
    }

    public void processOrderToWhatsApp(OrderRequestDto orderRequest) {
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("*New Order Placed* 🛍️\n\n");

        if (orderRequest.getCustomerDetails() != null) {
            orderDetails.append("*Name:* ").append(orderRequest.getCustomerDetails().getName()).append("\n");
            orderDetails.append("*Phone:* ").append(orderRequest.getCustomerDetails().getPhone()).append("\n");
            orderDetails.append("*Address:* ").append(orderRequest.getCustomerDetails().getAddressLine1()).append(", ")
                    .append(orderRequest.getCustomerDetails().getAddressLine2()).append("\n");

            if (orderRequest.getCustomerDetails().getLandmark() != null
                    && !orderRequest.getCustomerDetails().getLandmark().isEmpty()) {
                orderDetails.append("*Landmark:* ").append(orderRequest.getCustomerDetails().getLandmark())
                        .append("\n");
            }

            orderDetails.append("*City:* ").append(orderRequest.getCustomerDetails().getCity())
                    .append(" - ").append(orderRequest.getCustomerDetails().getPincode()).append("\n");
            orderDetails.append("*State:* ").append(orderRequest.getCustomerDetails().getState()).append("\n\n");
        }

        orderDetails.append("*Order Items:*\n");
        if (orderRequest.getItems() != null) {
            for (OrderItemDto item : orderRequest.getItems()) {
                String itemLine = "- " + item.getQuantity() + "x " + item.getTitle();
                if (item.getVariant() != null && !item.getVariant().isEmpty()) {
                    itemLine += " (" + item.getVariant() + ")";
                }
                itemLine += " (₹" + item.getPrice() + ")\n";
                orderDetails.append(itemLine);
            }
        }

        orderDetails.append("\n*Delivery:* ₹").append(orderRequest.getDeliveryCharge()).append("\n");
        orderDetails.append("*Total:* ₹").append(orderRequest.getTotalAmount()).append("\n");

        String payment = orderRequest.getPaymentMethod();
        if ("cod".equalsIgnoreCase(payment)) {
            payment = "Cash on Delivery";
        } else if (payment != null) {
            payment = payment.toUpperCase();
        }
        orderDetails.append("*Payment:* ").append(payment);

        log.info("Order processed for WhatsApp Notification. \nMessage Content:\n{}", orderDetails.toString());

        try {
            // Send to Shop Owner
            Message ownerMessage = Message.creator(
                    new PhoneNumber(twilioToPhone),
                    new PhoneNumber(twilioFromPhone),
                    orderDetails.toString()).create();

            log.info("WhatsApp notification sent successfully to Owner! Message SID: {}", ownerMessage.getSid());

            // Send to Customer
            if (orderRequest.getCustomerDetails() != null && orderRequest.getCustomerDetails().getPhone() != null) {
                String rawPhone = orderRequest.getCustomerDetails().getPhone().trim();
                if (!rawPhone.isEmpty()) {
                    // Prepend +91 if there's no country code
                    if (!rawPhone.startsWith("+")) {
                        rawPhone = "+91" + rawPhone;
                    }

                    String formattedCustomerPhone = "whatsapp:" + rawPhone;

                    Message customerMessage = Message.creator(
                            new PhoneNumber(formattedCustomerPhone),
                            new PhoneNumber(twilioFromPhone),
                            orderDetails.toString()).create();

                    log.info("WhatsApp notification sent successfully to Customer! Message SID: {}",
                            customerMessage.getSid());
                }
            }

        } catch (Exception e) {
            log.error("Failed to send WhatsApp notification via Twilio: {}", e.getMessage(), e);
        }
    }
}
