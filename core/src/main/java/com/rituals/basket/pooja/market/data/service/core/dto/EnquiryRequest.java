package com.rituals.basket.pooja.market.data.service.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnquiryRequest {

    private String name;
    private String phoneNumber;
    private String additionalDetails;
    private String gRecaptchaResponse;

}
