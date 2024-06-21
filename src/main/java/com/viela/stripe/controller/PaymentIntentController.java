package com.viela.stripe.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.viela.stripe.dto.RequestDto;
import com.viela.stripe.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentIntentController {

    @PostMapping("/create-payment")
    public ResponseEntity<ResponseDto> createPaymentIntent(@RequestBody RequestDto request) throws StripeException {
        try{
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(request.getAmount() * 100L)
                    .putMetadata("produto", request.getProductName())
                    .setCurrency("usd")
                    .setAutomaticPaymentMethods(
                            PaymentIntentCreateParams
                                    .AutomaticPaymentMethods
                                    .builder()
                                    .setEnabled(true)
                                    .build()
                    )
                    .build();
            PaymentIntent paymentIntent = PaymentIntent.create(params);

            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDto(paymentIntent.getId(), paymentIntent.getClientSecret()));
        }catch(StripeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("Error creating payment intent", e.getMessage()));
        }
    }
}
