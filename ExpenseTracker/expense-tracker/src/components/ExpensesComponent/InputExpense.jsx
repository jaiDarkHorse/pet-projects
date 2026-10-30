import { useState } from 'react';
import "./InputExpense.css";

export default function InputExpense({ expenses, setExpenses }) {
    const [description, setDescription] = useState("");
    const [category, setCategory] = useState("");
    const [amount, setAmount] = useState("");

    function addExpense() {
        if(!description||!category||!amount)
            return;
        const newExpenses = [...expenses,
        {
            id: crypto.randomUUID(),
            description: description,
            category: category,
            amount: Number(amount),
            date: new Date().toISOString().split("T")[0]
        }
        ];
        setExpenses(newExpenses);

        setDescription("");
        setCategory("");
        setAmount("");
    }

    return (
        <div className="input-exp">
            <p className='add-title'>Add Expense</p>
            <div className='input-cont'>
                <div className='input-item'>
                    <label>Description</label>
                    <input className="input-desc"
                        type="text"
                        placeholder="Enter Description"
                        onChange={(e) => setDescription(e.target.value)}
                        value={description} />

                </div>
                <div className='input-item'>
                <label>Category</label>
                <select
                    onChange={(e) => setCategory(e.target.value)}
                    value={category}>
                    <option value="">Select Category</option>
                    <option value="Food">Food</option>
                    <option value="Travel">Travel</option>
                    <option value="Shopping">Shopping</option>
                    <option value="Bills">Bills</option>
                    <option value="Entertainment">Entertainment</option>
                    <option value="Education">Education</option>
                    <option value="Health">Health</option>
                </select>
                </div>
                <div className='input-item'>
                <label>Amount</label>
                <input type="number" min="0"
                    placeholder="Enter Amount"
                    onChange={(e) => setAmount(e.target.value)}
                    value={amount} />
                </div>
                <button className="add-btn" onClick={addExpense}>Add</button>

            </div>

        </div>

    );
}