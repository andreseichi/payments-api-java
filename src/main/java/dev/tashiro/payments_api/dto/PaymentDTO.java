package dev.tashiro.payments_api.dto;

import java.util.Optional;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PaymentDTO(@NotNull(message = "Deve conter código de débito") int debitCode,
        @NotBlank(message = "Deve conter CPF/CNPJ do pagador") @Size(min = 11, max = 14, message = "CPF/CNPJ deve ser entre 11 e 14 caracteres") String payerCpfCnpj,
        @NotBlank(message = "Deve conter método de pagamento") @Size(max = 14, message = "Método de pagamento deve ter no máximo 14 caracteres") String paymentMethod,
        Optional<@CreditCardNumber(message = "Número do cartão de crédito inválido") String> cardNumber,
        @NotNull(message = "Deve conter valor do pagamento") long paymentValue) {

}