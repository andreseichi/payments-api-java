package dev.tashiro.payments_api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.tashiro.payments_api.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
  List<Payment> findByDebitCode(int debitCode);

  List<Payment> findByPayerCpfCnpj(String payerCpfCnpj);

  List<Payment> findByStatus(String status);

  List<Payment> findByPayerCpfCnpjAndStatus(String payerCpfCnpj, String status);

  List<Payment> findByDebitCodeAndPayerCpfCnpj(int debitCode, String payerCpfCnpj);

  List<Payment> findByDebitCodeAndStatus(int debitCode, String status);

  List<Payment> findByDebitCodeAndPayerCpfCnpjAndStatus(int debitCode, String payerCpfCnpj, String status);
}
