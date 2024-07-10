package com.example.backend.service.financial_item;

import com.example.backend.domain.FinancialItem;
import com.example.backend.repository.FinancialItemRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class FindAllFinancialItemsByYearMonthInternal {

    private final FinancialItemRepository repository;

    public FindAllFinancialItemsByYearMonthInternal(FinancialItemRepository repository) {
        this.repository = repository;
    }

    public List<FinancialItem> execute(Principal principal, Integer year, Integer month) {
        List<FinancialItem> allFinancialItems = this.repository.findByUserSecurityUsername(principal.getName());
        return allFinancialItems
                .stream()
                .filter(
                        outcome ->
                                year.equals(outcome.getCreationDate().getYear())
                                &&
                                month.equals(outcome.getCreationDate().getMonthValue())
                )
                .toList();
    }

}
