package com.viela.stripe.config;

import com.stripe.Stripe;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    @Value("${stripe.api.secretKey}")
    private String secretKey;

    @PostConstruct
    public void initSecretKey() {
        if (secretKey == null || secretKey.isEmpty()) {
            logger.error("stripe.api.secretKey is not set!");
        } else {
            logger.info("stripe.api.secretKey loaded successfully");
        }
        Stripe.apiKey = secretKey;
    }
}
