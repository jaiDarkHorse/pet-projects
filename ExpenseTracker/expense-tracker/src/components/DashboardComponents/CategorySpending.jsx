import './CategorySpending.css';

export default function CategorySpending({ expenses }) {
    function calculateCategoryTotals() {
        const categoryTotals = {};
        expenses.forEach((expense) => {
            if (categoryTotals[expense.category]) {
                categoryTotals[expense.category] += expense.amount;
            }
            else {
                categoryTotals[expense.category] = expense.amount;
            }
        });

        return categoryTotals;
    }

    const categoryTotals = calculateCategoryTotals();

    return (
        <div className="cat-cont">
            <div className="cat-title">
                <p>Spending by Category:</p>
            </div>
            <div className="cat-item-cont">
                {
                    Object.entries(categoryTotals).map(([category, amount]) => {
                        return (
                            <div className="cat-item" key={category}>
                                <p>{category}</p>
                                <span>₹{amount}</span>
                            </div>
                        );
                    })

                }
            </div>
        </div>
    );
}