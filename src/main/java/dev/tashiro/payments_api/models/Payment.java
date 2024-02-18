package dev.tashiro.payments_api.models;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.Length;

import dev.tashiro.payments_api.dto.PaymentDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "payments")
public class Payment {

  public Payment(PaymentDTO paymentDTO) {
    this.debitCode = paymentDTO.debitCode();
    this.payerCpfCnpj = paymentDTO.payerCpfCnpj();
    this.paymentMethod = paymentDTO.paymentMethod();
    this.cardNumber = paymentDTO.cardNumber().orElse(null);
    this.paymentValue = paymentDTO.paymentValue();
    this.createdAt = LocalDateTime.now();
  }

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(unique = true)
  private int debitCode;

  @Length(min = 11, max = 14)
  private String payerCpfCnpj;

  @Length(max = 14)
  private String paymentMethod;

  @CreditCardNumber
  private String cardNumber;

  @Positive
  @Min(value = 0)
  private long paymentValue;

  private String status = "Pendente de Processamento";

  @CreationTimestamp
  private LocalDateTime createdAt;
}
