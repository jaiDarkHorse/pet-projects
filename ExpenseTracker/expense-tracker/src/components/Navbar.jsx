import { Link } from "react-router-dom";
import "./Navbar.css";

export default function Navbar()
{
    return (
        <nav className="navbar">
            <div className="nav-logo">
                <Link to="/">Expense Tracker</Link>
            </div>

            <div className="nav-links">
                <Link to="/">Dashboard</Link>
                <Link to="/expenses">Expenses</Link>
                <Link to="/monthly-spending">Monthly Spending</Link>
            </div>
        </nav>
    );
}