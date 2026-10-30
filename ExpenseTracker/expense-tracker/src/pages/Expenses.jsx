import InputExpense from "../components/ExpensesComponent/InputExpense";
import ExpenseTable from "../components/ExpensesComponent/ExpenseTable"
import "./Expenses.css";

export default function Expenses({expenses,setExpenses})
{
    return (
        <div className="exp-page">
            <div className="exp-title">
                <p>Expenses</p>
            </div>
            <InputExpense expenses={expenses} setExpenses={setExpenses}/>
            <ExpenseTable expenses={expenses} setExpenses={setExpenses}/>
        </div>
    );
}