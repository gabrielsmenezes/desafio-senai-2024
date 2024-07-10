package com.example.backend.service.resume;

import com.example.backend.controller.resume.ResumeDTO;
import com.example.backend.domain.FinancialItem;
import com.example.backend.domain.Type;
import com.example.backend.service.financial_item.FindAllFinancialItemsByYearMonthInternal;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class FindResumeByYearMonth {
    private final FindAllFinancialItemsByYearMonthInternal findAllFinancialItemsByYearMonthInternal;

    public FindResumeByYearMonth(FindAllFinancialItemsByYearMonthInternal findAllFinancialItemsByYearMonthInternal) {
        this.findAllFinancialItemsByYearMonthInternal = findAllFinancialItemsByYearMonthInternal;
    }

    public ResumeDTO execute(Principal principal, Integer year, Integer month) {
        List<FinancialItem> items = this.findAllFinancialItemsByYearMonthInternal.execute(principal, year, month);
        Double income = items.stream().filter(financialItem -> Type.INCOME.equals(financialItem.getType())).mapToDouble(FinancialItem::getMonetaryValue).sum();
        Double outcome = items.stream().filter(financialItem -> Type.OUTCOME.equals(financialItem.getType())).mapToDouble(FinancialItem::getMonetaryValue).sum();
        return new ResumeDTO(income, outcome);
    }
}
