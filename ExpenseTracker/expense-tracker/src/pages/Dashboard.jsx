import SpendingOverview from "../components/DashboardComponents/SpendingOverview";
import RecentExpenses from "../components/DashboardComponents/RecentExpenses";
import CategorySpending from "../components/DashboardComponents/CategorySpending";

export default function Dashboard({expenses}){
    console.log("Dashboard expenses:", expenses);
    return(
        <>
        <SpendingOverview expenses={expenses}/>
        <RecentExpenses expenses={expenses}/>
        <CategorySpending expenses={expenses}/>
        </>
    );

}