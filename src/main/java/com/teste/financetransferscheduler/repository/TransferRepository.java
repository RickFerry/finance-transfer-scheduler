package com.teste.financetransferscheduler.repository;

import com.teste.financetransferscheduler.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
