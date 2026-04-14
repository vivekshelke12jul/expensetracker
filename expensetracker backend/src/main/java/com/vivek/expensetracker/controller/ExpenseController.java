package com.vivek.expensetracker.controller;

import com.vivek.expensetracker.exchange.request.ExpenseRequest;
import com.vivek.expensetracker.exchange.response.ExpenseResponse;
import com.vivek.expensetracker.model.Expense;
import com.vivek.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getAllExpenses(Authentication authentication) {
        String username = authentication.getName();
        List<ExpenseResponse> allExpenses = expenseService.getAllExpenses(username);
        return new ResponseEntity<>(allExpenses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(Authentication authentication, @PathVariable Long id) {
        String username = authentication.getName();
        ExpenseResponse expense = expenseService.getExpenseById(username, id).get();
        return new ResponseEntity<ExpenseResponse>(expense, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExpenseResponse> createExpense(Authentication authentication, @RequestBody ExpenseRequest expenseRequest) {
        String username = authentication.getName();
        ExpenseResponse createdExpense = expenseService.createExpense(username, expenseRequest);
        return new ResponseEntity<ExpenseResponse>(createdExpense, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(Authentication authentication, @PathVariable Long id, @RequestBody ExpenseRequest expenseRequest) {
        String username = authentication.getName();
        ExpenseResponse updatedExpense = expenseService.updateExpense(username, id, expenseRequest);
        return new ResponseEntity<ExpenseResponse>(updatedExpense, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> deleteExpense(Authentication authentication, @PathVariable Long id) {
        String username = authentication.getName();
        expenseService.deleteExpense(username, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
