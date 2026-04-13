package com.vivek.expensetracker.exchange.request;


import com.vivek.expensetracker.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseRequest {
    private String title;
    private String description;
    private Double amount;
    private Category category;
}
