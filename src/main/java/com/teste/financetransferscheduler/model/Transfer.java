package com.teste.financetransferscheduler.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Account number must be 10 digits")
    private String originAccount;

    @NotBlank
    @Pattern(regexp = "\\d{10}", message = "Account number must be 10 digits")
    private String destinationAccount;

    @NotNull
    @DecimalMin(value = "0.01", message = "Transfer amount must be greater than zero")
    private Double amount;

    private Double fee;

    @NotNull
    @FutureOrPresent(message = "Transfer date must be today or in the future")
    private LocalDate transferDate;

    @NotNull
    private LocalDate schedulingDate;
}
