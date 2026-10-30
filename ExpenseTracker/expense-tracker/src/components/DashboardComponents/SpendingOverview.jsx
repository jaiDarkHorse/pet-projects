import './SpendingOverview.css';

export default function SpendingOverview({ expenses }) {
    function calculateTotal() {
        let sum = 0;
        expenses.forEach((expense) => {
            sum += expense.amount;
        });
        return sum;
    }

    function calculateTotalThisMonth() {
        let sum = 0;
        const today = new Date();
        expenses.forEach((expense) => {
            const expenseDate = new Date(expense.date);
            if (expenseDate.getFullYear() === today.getFullYear() &&
                expenseDate.getMonth() === today.getMonth()) {
                sum += expense.amount;
            }
        });
        return sum;
    }

    function calculateExpenseCountThisMonth() {
        let count = 0;
        const today = new Date();
        expenses.forEach((expense) => {

            const expenseDate = new Date(expense.date);

            if (
                expenseDate.getFullYear() === today.getFullYear() &&
                expenseDate.getMonth() === today.getMonth()
            ) {
                count++;
            }

        });

        return count;
    }

    return (
        <div className='ov'>
            <p className='ov-title'>Here's your spending overview</p>
            <div className='ov-cont'>
                <div className='ov-cont-item'>
                    <p>Total Spent</p> <span>{calculateTotal()}</span>
                </div>
                <div className='ov-cont-item'>
                    <p>This Month Spent</p> <span>{calculateTotalThisMonth()}</span>
                </div>
                <div className='ov-cont-item'>
                    <p>Expenses Count</p> <span>{expenses.length}</span>
                </div>
                <div className='ov-cont-item'>
                    <p>This Month Count</p> <span>{calculateExpenseCountThisMonth()}</span>
                </div>
            </div>
        </div>
    );
}