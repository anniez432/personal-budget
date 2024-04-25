import java.util.LinkedList;
import java.util.Date;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Models personal budgets that users can add to their budget book.
 */
public class PersonalBudget {
    //data fields
    protected String name; //name of the personal budget
    protected LinkedList<Integer> expenses;
    protected LinkedList<String> expensesDescriptions;
    protected LinkedList<Integer> incomes;
    protected LinkedList<String> incomeDescriptions;
    protected Date date;
    protected int limit;
    protected boolean limitMet = false;

    //constructor

    public PersonalBudget(String name, Date date, int limit){
        if(name == null || name.isBlank()){
            throw new NullPointerException("Invalid name");
        }
        if(limit < 0){
            throw new NoSuchElementException("Invalid limit");
        } else if(limit == 0){
            this.limitMet = true;
        }
        this.name = name;
        this.date = date;
        this.limit = limit;
        this.expenses = new LinkedList<>();
        this.expensesDescriptions = new LinkedList<>();
        this.incomes = new LinkedList<>();
        this.incomeDescriptions = new LinkedList<>();
    }
    //methods

    //change name

    /**
     * Changes the name of the personal budget.
     * @param name The new name.
     */
    public void changeName(String name){
        if(name == null || name.isBlank()){
            throw new NullPointerException("Invalid name.");
        }
        this.name = name;
    }
    //change goal

    /**
     * Changes the limit budget amount of this personal budget
     * @param limit The new limit
     */
    public void changeGoal(int limit){
        if(limit < 0){
            throw new NoSuchElementException("Invalid limit.");
        }
        this.limit = limit;
    }

    //has limit been met yet

    /**
     * Determines if the budget's total expenses are greater
     * than the limit.
     * @return true if it has been met, false if not.
     */
    public boolean hasMetLimit(){
        if(totalExpenses() >= this.limit) {
            limitMet = true;
            return true;
        }
        else{
            limitMet = false;
            return false;
        }
    }

    //total income

    /**
     * Determines the total amount of income recorded in the budget
     * @return sum The total Integer income.
     */
    public Integer totalIncome(){
        Integer sum = 0;
        for(int i = 0; i < incomes.size(); ++i){
            sum += incomes.get(i);
        }

        return sum;
    }
    //total expenses

    /**
     * Determines the total amount of expenses recorded in the budget.
     * @return sum The total Integer amount of expenses
     */
    public Integer totalExpenses(){
        Integer sum = 0;
        for(int i = 0; i < expenses.size(); ++i){
            sum += expenses.get(i);
        }

        return sum;
    }
    //add expense - should also add description

    /**
     * Adds an expense and its description to the budget.
     * @param expense The expense to add
     * @param description The description of the expense to add
     */
    public void addExpense(Integer expense, String description){
        if(expense <= 0){
            throw new NoSuchElementException("Invalid expense");
        }
        expenses.add(expense);
        addExpenseDescription(description);
    }
    //add expense Description

    /**
     * Adds the description of an expense to the budget.
     * @param description The desciption to add.
     */
    private void addExpenseDescription(String description){
        expensesDescriptions.add(description);
    }
    //add income - should also add income description

    /**
     * Adds an income and its description to the budget
     * @param income The income to add
     * @param description Its description to add
     */
    public void addIncome(Integer income, String description){
        if(income <= 0){
            throw new NoSuchElementException("Invalid income");
        }
        incomes.add(income);
        addIncomeDescription(description);
    }

    /**
     * Adds the description of the income to the budget
     * @param description The description of the income to add.
     */
    private void addIncomeDescription(String description){
        incomeDescriptions.add(description);
    }
    //get ArrayList representation of expenses

    /**
     * Returns the ArrayList representation of the expenses.
     * @return expensesList The ArrayList representation.
     */
    public ArrayList<Integer> getListExpenses(){
        ArrayList<Integer> expensesList = new ArrayList<>();
        for (Integer items : expenses) {
            expensesList.add(items);
        }

        return expensesList;
        }

    //get ArrayList representation of income
    /**
     * Returns the ArrayList representation of the incomes.
     * @return incomeList The ArrayList representation.
     */
    public ArrayList<Integer> getListIncome(){
        ArrayList<Integer> incomeList = new ArrayList<>();
        for (Integer items : incomes) {
            incomeList.add(items);
        }

        return incomeList;
    }

    /**
     * Returns a string representation of the expenses, their descriptions and the incomes with their
     * descriptions.
     * @return
     */
    @Override
    public String toString(){
        String output = name + " (" + date.toString() + "):\nExpenses:\n ";
        for(int i = 0; i < getListExpenses().size(); ++i){
            output += "$"+expenses.get(i);
            output += ": " + expensesDescriptions.get(i) + "\n";
        }
        output += "Total Expenses: " + totalExpenses() + "\n" + "Income:\n";
        for(int i = 0; i < getListIncome().size(); ++i){
            output += "$"+incomes.get(i);
            output += ": " + incomeDescriptions.get(i) + "\n";
        }
        output += "Total Income: " + totalIncome() + "\n";

        return output;
    }
}