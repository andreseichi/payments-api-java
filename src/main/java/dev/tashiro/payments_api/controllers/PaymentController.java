package dev.tashiro.payments_api.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.tashiro.payments_api.dto.PaymentDTO;
import dev.tashiro.payments_api.dto.PaymentProcessDTO;
import dev.tashiro.payments_api.models.Payment;
import dev.tashiro.payments_api.services.PaymentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

  @Autowired
  private PaymentService paymentService;

  @PostMapping()
  public ResponseEntity<Object> create(@Valid @RequestBody PaymentDTO paymentDTO) {
    try {
      paymentService.create(paymentDTO);

      return new ResponseEntity<>("OK", HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
  }

  @GetMapping()
  public List<Payment> list() {
    return paymentService.listAll();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@Valid @RequestBody PaymentProcessDTO paymentProcessDTO,
      @PathVariable UUID id) {
    try {
      paymentService.update(id, paymentProcessDTO);

      return new ResponseEntity<>("OK", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable UUID id) {
    try {
      paymentService.deleteById(id);

      return ResponseEntity.noContent().build();
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
  }

}
