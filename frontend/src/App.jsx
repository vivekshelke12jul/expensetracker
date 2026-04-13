import React, { useState, useEffect } from "react";
import "./App.css";

const API_URL = "http://localhost:8080/api/expenses";

function App() {
  const [expenses, setExpenses] = useState([]);
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    amount: "",
    category: "",
  });

  // Load expenses on startup
  useEffect(() => {
    fetchExpenses();
  }, []);

  const fetchExpenses = async () => {
    try {
      const response = await fetch(API_URL);
      const data = await response.json();
      console.log(data);
      setExpenses(data);
    } catch (error) {}
  };

  const handleInputChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
    console.log(formData);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(formData)
      })
      const data = await response.json();
      console.log(data);
      setFormData({ title: '', description: '', amount: '', category: '', date: '' }); // Reset form
      fetchExpenses();
    } catch (error) {

    }
  };

  const deleteExpense = async (id) => {
    try {
      const response = await fetch(`${API_URL}/${id}`, {
        method: "DELETE",
      });
      await response.json();
      fetchExpenses();
      console.log(expenses);
    } catch (error) {

    }
  };

  return (
    <div className="container">
      <h1>Expense Tracker</h1>

      {/* Form Section */}
      <form onSubmit={handleSubmit} className="expense-form">
        <input
          name="title"
          placeholder="Title"
          value={formData.title}
          onChange={handleInputChange}
          required
        />
        <input
          name="description"
          placeholder="Description"
          value={formData.description}
          onChange={handleInputChange}
          required
        />
        <input
          name="amount"
          type="number"
          placeholder="Amount"
          value={formData.amount}
          onChange={handleInputChange}
          required
        />
        <input
          name="category"
          placeholder="Category (e.g. Food)"
          value={formData.category}
          onChange={handleInputChange}
          required
        />
        <button type="submit">Add Expense</button>
      </form>

      {/* List Section */}
      <div className="expense-list">
        <h2>History</h2>
        <table>
          <thead>
            <tr>
              <th>Title</th>
              <th>Amount</th>
              <th>Category</th>
              <th>Date</th>
              <th>Action</th>
            </tr>
          </thead>
          <tbody>
            {expenses.map((exp) => (
              <tr key={exp.id}>
                <td>{exp.title}</td>
                <td>${exp.amount}</td>
                <td>{exp.category}</td>
                <td>{exp.createdAt}</td>
                <td>
                  <button
                    onClick={() => deleteExpense(exp.id)}
                    className="delete-btn"
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default App;
