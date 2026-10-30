
export default function sortExpenses(expenses) {
        const recentExpenses = [...expenses];
        recentExpenses.sort((a, b) => {
            return new Date(b.date) - new Date(a.date);
        });
        return recentExpenses;
    }

export function groupExpensesByMonth(expenses){
    const groupedExpenses = {};

    expenses.forEach((expense)=>{
        const month = expense.date.slice(0,7);
        if(!groupedExpenses[month]){
            groupedExpenses[month] = [];
        }
        groupedExpenses[month].push(expense);
    })
    return groupedExpenses;
}

export function formatMonth(month) {
    const [year, monthNumber] = month.split("-");

    const date = new Date(
        Number(year),
        Number(monthNumber) - 1
    );

    return date.toLocaleString("en-US", {
        month: "long",
        year: "numeric"
    });
}