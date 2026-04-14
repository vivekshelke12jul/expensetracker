package com.vivek.expensetracker.repository;


import com.vivek.expensetracker.model.AppUser;
import com.vivek.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByAppUser(AppUser appUser);
    Optional<Expense> findByIdAndAppUser(Long id, AppUser appUser);
}
