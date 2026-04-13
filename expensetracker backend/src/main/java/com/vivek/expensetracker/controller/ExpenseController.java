package com.vivek.expensetracker.controller;

import com.vivek.expensetracker.exchange.request.ExpenseRequest;
import com.vivek.expensetracker.model.Expense;
import com.vivek.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin("*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {
        List<Expense> allExpenses = expenseService.getAllExpenses();
        return new ResponseEntity<>(allExpenses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id);
        return new ResponseEntity<Expense>(expense, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody ExpenseRequest expenseRequest) {
        Expense createdExpense = expenseService.createExpense(expenseRequest);
        return new ResponseEntity<Expense>(createdExpense, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody ExpenseRequest expenseRequest) {
        Expense updatedExpense = expenseService.updateExpense(id, expenseRequest);
        return new ResponseEntity<Expense>(updatedExpense, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Expense> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
