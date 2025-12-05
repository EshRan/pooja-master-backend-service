package com.rituals.basket.pooja.market.data.service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@OpenAPIDefinition(
        info = @Info(
                title = "Pooja API",
                version = "1.0.0",
                description = "API documentation for Pooja Items and Occasions",
                contact = @Contact(name = "EshRan", email = "deswari2891@gmail.com")
        )
)
@SpringBootApplication(scanBasePackages = {
        "com.rituals.basket.pooja.market.data.service.core"
})
public class PoojaMasterBackendServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PoojaMasterBackendServiceApplication.class, args);
	}

}
