package com.example.backend.controller.resume;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResumeDTO {
    private Double balance;
    private Double income;
    private Double outcome;

    public ResumeDTO(Double income, Double outcome){
        this.income = income;
        this.outcome = outcome;
        this.balance = income - outcome;
    }

}
