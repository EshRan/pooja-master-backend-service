package com.rituals.basket.pooja.market.data.service.core.dto;

import java.util.List;

public class OrderRequestDto {
    private CustomerDetailsDto customerDetails;
    private List<OrderItemDto> items;
    private String paymentMethod;
    private Double deliveryCharge;
    private Double totalAmount;

    public CustomerDetailsDto getCustomerDetails() {
        return customerDetails;
    }

    public void setCustomerDetails(CustomerDetailsDto customerDetails) {
        this.customerDetails = customerDetails;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDto> items) {
        this.items = items;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getDeliveryCharge() {
        return deliveryCharge;
    }

    public void setDeliveryCharge(Double deliveryCharge) {
        this.deliveryCharge = deliveryCharge;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
