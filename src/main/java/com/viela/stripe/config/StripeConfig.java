package com.viela.stripe.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    @Value("${stripe.api.secretKey}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;

        /*
        métodos para definir os pagamentos:
        PaymentMethodCreateParams paymentMethodCreateParams = PaymentMethodCreateParams.builder()
                .set
                .build();
         */
    }
}
