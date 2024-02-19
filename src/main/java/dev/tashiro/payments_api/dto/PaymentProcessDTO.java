package dev.tashiro.payments_api.dto;

import dev.tashiro.payments_api.models.Status;

public record PaymentProcessDTO(
        Status status) {

}
