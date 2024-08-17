package com.teste.financetransferscheduler.service;

import com.teste.financetransferscheduler.model.Transfer;
import com.teste.financetransferscheduler.repository.TransferRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
@AllArgsConstructor
public class TransferService {

    private final TransferRepository transferRepository;

    public Transfer scheduleTransfer(Transfer transfer) {
        long daysBetween = ChronoUnit.DAYS.between(transfer.getSchedulingDate(), transfer.getTransferDate());

        double fee = calculateFee(daysBetween, transfer.getAmount());
        if (fee == -1) {
            throw new IllegalArgumentException("No applicable fee for the scheduled transfer.");
        }

        transfer.setFee(fee);
        return transferRepository.save(transfer);
    }

    private double calculateFee(long daysBetween, double amount) {
        if (daysBetween <= 0) {
            return amount <= 3 ? 0.025 * amount : -1;
        } else if (daysBetween <= 10) {
            return amount <= 12 ? 0.00 : -1;
        } else if (daysBetween <= 20) {
            return 0.082 * amount;
        } else if (daysBetween <= 30) {
            return 0.069 * amount;
        } else if (daysBetween <= 40) {
            return 0.047 * amount;
        } else if (daysBetween <= 50) {
            return 0.017 * amount;
        } else {
            return -1;
        }
    }

    public Iterable<Transfer> listAllTransfers() {
        return transferRepository.findAll();
    }
}
