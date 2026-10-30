import sortExpenses from '../../utils/helper';
import { useState } from 'react';
import "./ExpenseTable.css";

export default function ExpenseTable({ expenses, setExpenses }) {
    const [editingId, setEditingId] = useState(null);
    const [editDescription, setEditDescription] = useState("");
    const [editAmount, setEditAmount] = useState("");
    const [editCategory, setEditCategory] = useState("");
    const [categoryFilter, setCategoryFilter] = useState("All");

    const sortedExpenses = sortExpenses(expenses);

    const filteredExpenses = sortedExpenses.filter((expense) => {
        if (categoryFilter === "All") {
            return true;
        }
        return expense.category === categoryFilter;
    });
    function deleteExpense(id) {
        setExpenses((prevExpenses) => {
            const newExpenses = prevExpenses.filter((expense) => {
                return expense.id !== id;
            });
            return newExpenses;
        });
    }

    function saveExpense(id) {
        setExpenses((prevExpenses) => {
            return prevExpenses.map((expense) => {
                if (expense.id === id) {
                    return {
                        ...expense,
                        description: editDescription,
                        amount: Number(editAmount),
                        category: editCategory,
                    };
                }
                return expense;
            })
        })
        setEditingId(null);
    }

    return (
        <div className="exp-tab-cont">
            <table>
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>
                            <select className = "cat-filter"
                                value={categoryFilter}
                                onChange={(e) => setCategoryFilter(e.target.value)}
                            >
                                <option value="All">Category</option>
                                <option value="Food">Food</option>
                                <option value="Travel">Travel</option>
                                <option value="Shopping">Shopping</option>
                                <option value="Bills">Bills</option>
                                <option value="Entertainment">Entertainment</option>
                                <option value="Education">Education</option>
                                <option value="Health">Health</option>
                            </select>
                        </th>
                        <th>Amount</th>
                        <th>Date</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {filteredExpenses.map((expense) => {
                        return (
                            <tr key={expense.id}>
                                <td>
                                    {editingId === expense.id ? (
                                        <input className='input-desc-edit'
                                            type="text"
                                            value={editDescription}
                                            onChange={(e) => setEditDescription(e.target.value)}
                                        />
                                    ) : (
                                        expense.description
                                    )}
                                </td>
                                <td>
                                    {editingId === expense.id ? (
                                        <select
                                            value={editCategory}
                                            onChange={(e) => setEditCategory(e.target.value)}
                                        >
                                            <option value="">Select Category</option>
                                            <option value="Food">Food</option>
                                            <option value="Travel">Travel</option>
                                            <option value="Shopping">Shopping</option>
                                            <option value="Bills">Bills</option>
                                            <option value="Entertainment">Entertainment</option>
                                            <option value="Education">Education</option>
                                            <option value="Health">Health</option>
                                        </select>
                                    ) : (
                                        expense.category
                                    )}
                                </td>
                                <td>₹
                                    {editingId === expense.id ? (
                                        <input
                                            className="input-amt-edit"
                                            type="number"
                                            min="0"
                                            value={editAmount}
                                            onChange={(e) => setEditAmount(e.target.value)}
                                        />
                                    ) : (
                                        expense.amount
                                    )}</td>
                                <td>{expense.date}</td>
                                <td className="table-btn-cont">
                                    {editingId === expense.id ? (
                                        <button className='save-btn'
                                            onClick={() => saveExpense(expense.id)}>Save</button>
                                    ) : (
                                        <button className='edit-btn'
                                            onClick={() => {
                                                setEditingId(expense.id)
                                                setEditDescription(expense.description)
                                                setEditAmount(expense.amount)
                                                setEditCategory(expense.category)
                                            }}>Edit</button>
                                    )}
                                    <button className='del-btn'
                                        onClick={() => deleteExpense(expense.id)}>Delete</button>
                                </td>
                            </tr>
                        );
                    })}
                </tbody>
            </table>
        </div>
    );
}