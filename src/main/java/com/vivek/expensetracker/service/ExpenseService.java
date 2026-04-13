package com.vivek.expensetracker.service;

import com.vivek.expensetracker.exchange.request.ExpenseRequest;
import com.vivek.expensetracker.model.Expense;
import com.vivek.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    public Expense createExpense(ExpenseRequest expenseRequest) {
        Expense expense = new Expense();
        expense.setTitle(expenseRequest.getTitle());
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(expenseRequest.getCategory());
        return expenseRepository.save(expense);
    }

    public Expense updateExpense(Long id, ExpenseRequest expenseRequest) {
        Expense expense = getExpenseById(id);
        if (expense == null) {
            return null;
        }
        expense.setTitle(expenseRequest.getTitle());
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(expenseRequest.getCategory());
        return expenseRepository.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
