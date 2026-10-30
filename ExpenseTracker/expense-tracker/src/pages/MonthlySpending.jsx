import { groupExpensesByMonth, formatMonth } from "../utils/helper";
import { useState } from "react";
import "./MonthlySpending.css";

export default function MonthlySpending({ expenses }) {
    const [selectedMonth, setSelectedMonth] = useState(null);
    const [selectedMonthName, setSelectedMonthName] = useState("");
    const selectedMonthTotal = selectedMonth ? selectedMonth.reduce((sum, expense) => {
        return sum + expense.amount;
    }, 0) : 0;

    const groupedExpenses = groupExpensesByMonth(expenses);

    return (
        <div className="month-spend-cont">

            <h1 className="monthly-title">Monthly Spending</h1>

            {Object.entries(groupedExpenses).map(([month, monthExpenses]) => {

                const total = monthExpenses.reduce((sum, expense) => {
                    return sum + expense.amount;
                }, 0);

                return (
                    <div className="month-card" key={month}>
                        <h2>{formatMonth(month)}</h2>
                        <div className="month-card-amount-exp">
                            <h3>₹{total}</h3>
                            <p>{monthExpenses.length} expenses</p>

                        </div>

                        <p className="view-spend"
                            onClick={() => {
                                setSelectedMonth(monthExpenses);
                                setSelectedMonthName(formatMonth(month));
                            }}
                        >
                            View Spendings
                        </p>
                    </div>
                );
            })}

            {selectedMonth && (
                <div className="modal-container">
                    <div className="spending-popup">
                        <div className="popup-header">
                            <p>{selectedMonthName} Expenses</p>
                            <button onClick={() => setSelectedMonth(null)}>
                                X
                            </button>
                        </div>
                        {selectedMonth.map((expense) => {
                            return (
                                <div key={expense.id} className="popup-item">
                                    <p>{expense.description}</p>
                                    <p>₹{expense.amount}</p>
                                </div>
                            );
                        })}
                        <div className="popup-total">
                            <p>Total</p>
                            <p>₹{selectedMonthTotal}</p>
                        </div>
                    </div>
                </div>
            )}

        </div>
    );


}
