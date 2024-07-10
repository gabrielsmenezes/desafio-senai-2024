package com.example.backend.repository;

import com.example.backend.domain.FinancialItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinancialItemRepository extends JpaRepository<FinancialItem, Long> {
    Page<FinancialItem> findByUserSecurityUsername(Pageable pageable, String username);
    List<FinancialItem> findByUserSecurityUsername(String username);
}
