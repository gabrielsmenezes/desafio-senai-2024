package com.example.backend.service.financial_item;

import com.example.backend.controller.financial.FinancialItemDTO;
import com.example.backend.repository.FinancialItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class FindAllFinancialItemsPaged {

    private final FinancialItemRepository repository;
    private final ModelMapper mapper;

    public FindAllFinancialItemsPaged(FinancialItemRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Page<FinancialItemDTO> execute(Principal principal, Pageable pageable) {
        return this.repository.findByUserSecurityUsername(pageable, principal.getName()).map(financialItem -> this.mapper.map(financialItem, FinancialItemDTO.class));
    }
}
