package com.vivek.expensetracker.exchange.transformer;

import com.vivek.expensetracker.exchange.response.ExpenseResponse;
import com.vivek.expensetracker.model.Expense;

public class ExpenseTransformer {
    public static ExpenseResponse expenseToExpenseResponse(Expense expense){
        return ExpenseResponse.builder()
                .id(expense.getId())
                .title(expense.getTitle())
                .description(expense.getDescription())
                .amount(expense.getAmount())
                .category(expense.getCategory())
                .build();
    }
}
