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
public class DeleteFinancialItem {
    private final UserSecurityRepository userSecurityRepository;
    private final FinancialItemRepository repository;
    private final ModelMapper modelMapper;



    public DeleteFinancialItem(UserSecurityRepository userSecurityRepository, FinancialItemRepository repository, ModelMapper modelMapper) {
        this.userSecurityRepository = userSecurityRepository;
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public FinancialItemDTO execute(Principal principal, Long financialItemId) throws Exception {
        UserSecurity userSecurity = this.userSecurityRepository.findByUsername(principal.getName()).orElseThrow();
        FinancialItem financialItem = this.repository.getReferenceById(financialItemId);
        if (!financialItem.getUserSecurity().equals(userSecurity)){
            throw new Exception("You cannot delete FinancialItem from anothers");
        }
        this.repository.delete(financialItem);
        return this.modelMapper.map(financialItem, FinancialItemDTO.class);
    }
}
