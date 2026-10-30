import './App.css'
import { Routes, Route } from 'react-router-dom';
import Dashboard from './pages/Dashboard';
import { useState, useEffect } from 'react';
import Expenses from './pages/Expenses';
import MonthlySpending from "./pages/MonthlySpending";
import Navbar from "./components/Navbar";

function App() {
    const initialExpenses = [
        {
            id: 1,
            description: "Lunch",
            amount: 350,
            category: "Food",
            date: "2026-09-25"
        },
        {
            id: 2,
            description: "Bus Ticket",
            amount: 40,
            category: "Travel",
            date: "2026-09-24"
        },
        {
            id: 3,
            description: "New Shoes",
            amount: 2500,
            category: "Shopping",
            date: "2026-09-23"
        },
        {
            id: 4,
            description: "Internet Bill",
            amount: 799,
            category: "Bills",
            date: "2026-09-22"
        },
        {
            id: 5,
            description: "Movie Ticket",
            amount: 450,
            category: "Entertainment",
            date: "2026-09-20"
        },
        {
            id: 6,
            description: "Groceries",
            amount: 1850,
            category: "Food",
            date: "2026-09-18"
        },
        {
            id: 7,
            description: "Uber",
            amount: 280,
            category: "Travel",
            date: "2026-09-15"
        },
        {
            id: 8,
            description: "Online Course",
            amount: 1200,
            category: "Education",
            date: "2026-09-10"
        },
        {
            id: 9,
            description: "Restaurant Dinner",
            amount: 950,
            category: "Food",
            date: "2026-08-28"
        },
        {
            id: 10,
            description: "Electricity Bill",
            amount: 1350,
            category: "Bills",
            date: "2026-08-25"
        },
        {
            id: 11,
            description: "Train Ticket",
            amount: 620,
            category: "Travel",
            date: "2026-08-18"
        },
        {
            id: 12,
            description: "T-Shirt",
            amount: 899,
            category: "Shopping",
            date: "2026-08-12"
        },
        {
            id: 13,
            description: "Groceries",
            amount: 2100,
            category: "Food",
            date: "2026-07-27"
        },
        {
            id: 14,
            description: "Gym Membership",
            amount: 1500,
            category: "Health",
            date: "2026-07-20"
        },
        {
            id: 15,
            description: "Movie Ticket",
            amount: 500,
            category: "Entertainment",
            date: "2026-07-15"
        }];

    const [expenses, setExpenses] = useState(() => {
        const savedExpenses = localStorage.getItem("expenses");
        if (savedExpenses) {
            return JSON.parse(savedExpenses);
        }
        return initialExpenses;
    });

    useEffect(() => {
        localStorage.setItem("expenses", JSON.stringify(expenses));
    }, [expenses]);

    return (
        <>
            <Navbar />
            <Routes>
                <Route path="/" element={<Dashboard expenses={expenses} />} />
                <Route path="/expenses" element={<Expenses expenses={expenses} setExpenses={setExpenses} />} />
                <Route path="monthly-spending" element={<MonthlySpending expenses={expenses} />} />
            </Routes>
        </>


    );

}

export default App
