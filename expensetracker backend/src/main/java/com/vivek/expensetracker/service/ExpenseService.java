package com.vivek.expensetracker.service;

import com.vivek.expensetracker.exchange.request.ExpenseRequest;
import com.vivek.expensetracker.exchange.response.ExpenseResponse;
import com.vivek.expensetracker.exchange.transformer.ExpenseTransformer;
import com.vivek.expensetracker.model.AppUser;
import com.vivek.expensetracker.model.Expense;
import com.vivek.expensetracker.repository.ExpenseRepository;
import com.vivek.expensetracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ExpenseResponse> getAllExpenses(String username) {
        AppUser user = userRepository.findByUsername(username).orElse(null);
        return expenseRepository.findByAppUser(user).stream().map(ExpenseTransformer::expenseToExpenseResponse).toList();
    }

    public Optional<ExpenseResponse> getExpenseById(String username, Long id) {
        AppUser user = userRepository.findByUsername(username).orElse(null);
        return expenseRepository.findByIdAndAppUser(id, user).map(ExpenseTransformer::expenseToExpenseResponse);
    }

    public ExpenseResponse createExpense(String username, ExpenseRequest expenseRequest) {
        AppUser user = userRepository.findByUsername(username).orElse(null);

        Expense expense = new Expense();
        expense.setTitle(expenseRequest.getTitle());
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(expenseRequest.getCategory());
        expense.setAppUser(user);

        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseTransformer.expenseToExpenseResponse(savedExpense);
    }

    public ExpenseResponse updateExpense(String username, Long id, ExpenseRequest expenseRequest) {
        AppUser user = userRepository.findByUsername(username).orElse(null);
        Optional<Expense> oExpense = expenseRepository.findByIdAndAppUser(id, user);
        if (oExpense.isEmpty()) {
            return null;
        }
        Expense expense = oExpense.get();
        expense.setTitle(expenseRequest.getTitle());
        expense.setDescription(expenseRequest.getDescription());
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(expenseRequest.getCategory());

        Expense savedExpense = expenseRepository.save(expense);
        return ExpenseTransformer.expenseToExpenseResponse(savedExpense);
    }

    public void deleteExpense(String username, Long id) {
        AppUser user = userRepository.findByUsername(username).orElse(null);
        Optional<Expense> oExpense = expenseRepository.findByIdAndAppUser(id, user);

        if (oExpense.isEmpty()) {
            return;
        }
        expenseRepository.deleteById(id);
    }
}
