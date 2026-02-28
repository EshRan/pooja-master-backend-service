package com.rituals.basket.pooja.market.data.service.core.controller;

import com.rituals.basket.pooja.market.data.service.core.dto.EnquiryRequest;
import com.rituals.basket.pooja.market.data.service.core.model.Enquiry;
import com.rituals.basket.pooja.market.data.service.core.repository.EnquiryRepository;
import com.rituals.basket.pooja.market.data.service.core.service.CaptchaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/enquiries")
@RequiredArgsConstructor
@Tag(name = "Enquiry", description = "Operations related to Enquiries")
public class EnquiryController {

    private final CaptchaService captchaService;
    private final EnquiryRepository enquiryRepository;

    @PostMapping
    @Operation(summary = "Submit a new enquiry with reCAPTCHA verification")
    public ResponseEntity<?> submitEnquiry(@RequestBody EnquiryRequest request, HttpServletRequest httpRequest) {

        String ipAddress = httpRequest.getRemoteAddr();
        log.info("Received enquiry submission from IP: {}", ipAddress);

        // Verify reCAPTCHA
        boolean isCaptchaValid = captchaService.verify(ipAddress, request.getGRecaptchaResponse());
        if (!isCaptchaValid) {
            log.warn("Invalid reCAPTCHA for IP: {}", ipAddress);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or missing reCAPTCHA token.");
        }

        // Save Enquiry
        Enquiry enquiry = new Enquiry();
        enquiry.setName(request.getName());
        enquiry.setPhoneNumber(request.getPhoneNumber());
        enquiry.setAdditionalDetails(request.getAdditionalDetails());
        enquiry.setIpAddress(ipAddress);

        enquiryRepository.save(enquiry);

        log.info("Successfully saved enquiry for user: {}", enquiry.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body("Enquiry submitted successfully.");
    }
}
