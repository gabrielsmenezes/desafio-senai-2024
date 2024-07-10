package com.example.backend.service.financial_item;

import com.example.backend.controller.financial.FinancialItemDTO;
import com.example.backend.domain.FinancialItem;
import com.example.backend.domain.UserSecurity;
import com.example.backend.repository.FinancialItemRepository;
import com.example.backend.repository.UserSecurityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class SaveFinancialItem {

    private final FinancialItemRepository repository;
    private final ModelMapper modelMapper;
    private final UserSecurityRepository userSecurityRepository;

    public SaveFinancialItem(FinancialItemRepository repository, ModelMapper modelMapper, UserSecurityRepository userSecurityRepository) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.userSecurityRepository = userSecurityRepository;
    }

    public FinancialItemDTO execute(Principal principal, FinancialItemDTO financialItemDTO) {
        UserSecurity userSecurity = this.userSecurityRepository.findByUsername(principal.getName()).orElseThrow();
        FinancialItem financialItem = this.modelMapper.map(financialItemDTO, FinancialItem.class);
        financialItem.setUserSecurity(userSecurity);
        FinancialItem saved = this.repository.save(financialItem);
        return this.modelMapper.map(saved, FinancialItemDTO.class);
    }

}
