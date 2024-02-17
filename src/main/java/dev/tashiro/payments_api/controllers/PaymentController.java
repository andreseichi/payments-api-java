package dev.tashiro.payments_api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.tashiro.payments_api.entities.PaymentEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

  @PostMapping()
  public void create(@Valid @RequestBody PaymentEntity paymentEntity) {
    System.out.println(paymentEntity.getPaymentMethod());
  }

}
