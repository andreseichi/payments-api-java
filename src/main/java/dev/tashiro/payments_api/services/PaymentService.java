package dev.tashiro.payments_api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.tashiro.payments_api.dto.PaymentDTO;
import dev.tashiro.payments_api.models.Payment;
import dev.tashiro.payments_api.repositories.PaymentRepository;

@Service
public class PaymentService {

  @Autowired
  private PaymentRepository paymentRepository;

  public void create(PaymentDTO req) {
    Payment payment = new Payment(req);

    Optional<Payment> paymentFromDatabase = paymentRepository.findByDebitCode(payment.getDebitCode());

    if (paymentFromDatabase.isPresent())
      throw new RuntimeException("Código de débito já existente!");

    paymentRepository.save(payment);
  }
}
