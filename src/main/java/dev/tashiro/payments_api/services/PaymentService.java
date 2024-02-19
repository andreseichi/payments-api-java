package dev.tashiro.payments_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

  public List<Payment> listAll() {
    return paymentRepository.findAll();
  }

  public void deleteById(UUID id) {
    Optional<Payment> paymentFromDatabase = paymentRepository.findById(id);

    if (paymentFromDatabase.isPresent()) {
      Payment payment = paymentFromDatabase.get();

      String status = payment.getStatus();

      if (status.equals("Pendente de Processamento")) {
        paymentRepository.deleteById(id);
      } else {
        throw new RuntimeException("Pagamento não pode ser excluído!");
      }

    } else {
      throw new RuntimeException("Pagamento não encontrado!");
    }

  }
}
