package dev.tashiro.payments_api.entities;

import java.util.UUID;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PaymentEntity {

  private UUID id;

  private int debitCode;

  private String cpf;
  private String cnpj;

  private String paymentMethod;

  @Pattern(regexp = "\\d{16}", message = "Número do cartão deve conter 16 dígitos")
  private String cardNumber;
  private int value;
}
