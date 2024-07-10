package com.example.backend.controller.financial;

import com.example.backend.domain.Category;
import com.example.backend.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialItemDTO {

    private Long id;

    @NotEmpty(message = "Description is a required field")
    private String description;

    @NotNull(message = "MonetaryValue is a required field")
    @Positive(message = "MonetaryValue must be positive")
    private Double monetaryValue;

    @NotEmpty(message = "CreationDate is a required field")
    private String creationDate;

    private Category category;

    private Type type;

}
