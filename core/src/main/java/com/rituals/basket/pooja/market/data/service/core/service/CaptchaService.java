package com.rituals.basket.pooja.market.data.service.core.service;

import com.rituals.basket.pooja.market.data.service.core.dto.CaptchaResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class CaptchaService {

    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;

    private final RestTemplate restTemplate;

    public boolean verify(String ip, String response) {
        try {
            String url = "https://www.google.com/recaptcha/api/siteverify?secret="
                    + recaptchaSecret + "&response=" + response + "&remoteip=" + ip;

            log.info("Verifying captcha response for IP: {}", ip);
            CaptchaResponse res = restTemplate.postForObject(url, null, CaptchaResponse.class);

            if (res != null && res.isSuccess()) {
                return true;
            } else {
                log.warn("Captcha verification failed. Response: {}, Error codes: {}",
                        res != null ? res.isSuccess() : "null",
                        res != null ? res.getErrorCodes() : "null");
                return false;
            }
        } catch (Exception e) {
            log.error("Error during captcha verification", e);
            return false;
        }
    }
}
