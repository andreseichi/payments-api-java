package dev.tashiro.payments_api.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.tashiro.payments_api.dto.PaymentDTO;
import dev.tashiro.payments_api.dto.PaymentProcessDTO;
import dev.tashiro.payments_api.models.Payment;
import dev.tashiro.payments_api.models.Status;
import dev.tashiro.payments_api.repositories.PaymentRepository;
import dev.tashiro.payments_api.utils.Utils;

@Service
public class PaymentService {

  @Autowired
  private PaymentRepository paymentRepository;

  public Payment create(PaymentDTO req) {
    Payment payment = new Payment(req);

    return paymentRepository.save(payment);
  }

  public List<Payment> listAll(Integer debitCode, String payerCpfCnpj, String status) {
    Utils.RemoveEmptyParams(payerCpfCnpj);
    Utils.RemoveEmptyParams(status);

    if (debitCode != null && payerCpfCnpj != null && status != null)
      return paymentRepository.findByDebitCodeAndPayerCpfCnpjAndStatus(debitCode, payerCpfCnpj, status);
    else if (debitCode != null && payerCpfCnpj != null)
      return paymentRepository.findByDebitCodeAndPayerCpfCnpj(debitCode, payerCpfCnpj);
    else if (debitCode != null && status != null)
      return paymentRepository.findByDebitCodeAndStatus(debitCode, status);
    else if (payerCpfCnpj != null && status != null)
      return paymentRepository.findByPayerCpfCnpjAndStatus(payerCpfCnpj, status);
    else if (debitCode != null)
      return paymentRepository.findByDebitCode(debitCode);
    else if (payerCpfCnpj != null)
      return paymentRepository.findByPayerCpfCnpj(payerCpfCnpj);
    else if (status != null)
      return paymentRepository.findByStatus(status);
    else
      return paymentRepository.findAll();

  }

  public Payment update(UUID id, PaymentProcessDTO req) {
    Optional<Payment> paymentFromDatabase = paymentRepository.findById(id);

    if (paymentFromDatabase.isEmpty()) {
      throw new RuntimeException("Pagamento não encontrado!");
    }

    Payment payment = paymentFromDatabase.get();
    Status status = req.status();

    if (payment.getStatus().equals("Pendente de Processamento")) {
      switch (status) {
        case APPROVED:
          payment.setStatus("Processado com Sucesso");
          break;
        case DENIED:
          payment.setStatus("Processado com Falha");
          break;
        default:
          throw new RuntimeException("Status inválido!");
      }
    } else if (payment.getStatus().equals("Processado com Falha") && status.equals(Status.PENDENT)) {
      payment.setStatus("Pendente de Processamento");
    } else {
      throw new RuntimeException("Pagamento não pode ser atualizado!");
    }

    Payment paymentUpdated = paymentRepository.save(payment);

    return paymentUpdated;
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
