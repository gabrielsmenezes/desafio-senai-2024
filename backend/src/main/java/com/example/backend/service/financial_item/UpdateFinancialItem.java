package com.example.backend.service.financial_item;

import com.example.backend.controller.financial.FinancialItemDTO;
import com.example.backend.domain.FinancialItem;
import com.example.backend.repository.FinancialItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UpdateFinancialItem {

    private final ModelMapper mapper;
    private final FinancialItemRepository financialItemRepository;

    public UpdateFinancialItem(ModelMapper mapper, FinancialItemRepository financialItemRepository) {
        this.mapper = mapper;
        this.financialItemRepository = financialItemRepository;
    }

    public FinancialItemDTO execute(Principal principal, FinancialItemDTO financialItemDTO) throws Exception {
        FinancialItem financialItem = this.mapper.map(financialItemDTO, FinancialItem.class);
        if (principal.equals(financialItem.getUserSecurity())) {
            throw new Exception("You cannot edit financial items from another user");
        }
        FinancialItem savedFinancialItem = this.financialItemRepository.save(financialItem);
        return this.mapper.map(savedFinancialItem, FinancialItemDTO.class);
    }
}
