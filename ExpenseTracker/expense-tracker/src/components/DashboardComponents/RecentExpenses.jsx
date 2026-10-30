import './RecentExpenses.css';
import sortExpenses from '../../utils/helper';

export default function RecentExpenses({ expenses }) {

    const recentExpenses = sortExpenses(expenses).slice(0,5);

    return (
        <div className='re-cont'>
            <div className="re-title">
                <p>Recent Expenses</p>
            </div>
            <div className="re-expenses">
                <table>
                    <thead>
                        <tr>
                            <th>Description</th>
                            <th>Category</th>
                            <th>Amount</th>
                            <th>Date</th>
                        </tr>
                    </thead>
                    <tbody>
                        {recentExpenses.map((expense) => {
                            return (
                                <tr key={expense.id}>
                                    <td>{expense.description}</td>
                                    <td>{expense.category}</td>
                                    <td>₹{expense.amount}</td>
                                    <td>{expense.date}</td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            </div>
        </div>
    );
}