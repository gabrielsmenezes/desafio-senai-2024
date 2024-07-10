package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FinancialItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double monetaryValue;

    @Column(nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'OTHER'")
    @Enumerated(EnumType.STRING)
    private Category category = Category.OTHER;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'OUTCOME'")
    @Enumerated(EnumType.STRING)
    private Type type = Type.OUTCOME;

    @ManyToOne
    private UserSecurity userSecurity;
}
