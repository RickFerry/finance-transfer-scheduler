package com.teste.financetransferscheduler.controller;

import com.teste.financetransferscheduler.model.Transfer;
import com.teste.financetransferscheduler.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<?> scheduleTransfer(@Valid @RequestBody Transfer transfer) {
        try {
            Transfer scheduledTransfer = transferService.scheduleTransfer(transfer);
            return ResponseEntity.status(HttpStatus.CREATED).body(scheduledTransfer);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public ResponseEntity<Iterable<Transfer>> listTransfers() {
        return ResponseEntity.ok(transferService.listAllTransfers());
    }
}
