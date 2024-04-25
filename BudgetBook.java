import java.util.Date;
import java.util.LinkedList;
//annie
/**
 * Class that creates a budget book for the user that contains their various budgets.
 */
public class BudgetBook {

    //data fields
    protected String name; //name of the budget
    protected Date date; //date of creation
    protected int size; //number of budgets within the budget book

    protected LinkedList<PersonalBudget> budgetBook;
    private LinkedList<String> budgetNames;
    //constructors
    public BudgetBook(String name, Date date){
        if(name==null || name.isBlank()){
            throw new NullPointerException("Invalid name.");
        }
        this.name = name;
        this.date = date;
        this.size = 0;
        budgetBook = new LinkedList<>();
        budgetNames = new LinkedList<>();
    }

    //methods

    /**
     * Adds the user's budget to the BudgetBook.
     * @param budget The budget to be added.
     */
    public void addBudget(PersonalBudget budget){
        budgetBook.add(budget);
        addBudgetName(budget.name);
        size++;
    }

    private void addBudgetName(String name){
        budgetNames.add(name);
    }

    /**
     * Removes a budget from the Budgetbook.
     * @param budget The budget to be removed.
     */
    public void removeBudget(String budget){
        if(!budgetNames.contains(budget)){
            System.out.println("Invalid budget. Enter a valid budget name.");
        } else {
            int index = 0;
           //find index of name
            for(int i = 0; i < budgetNames.size(); ++i){
                if(budgetNames.get(i).equals(budget)){
                    index = i;
                    break;
                }
            }
            //remove name
            budgetNames.remove(budget);
            //remove budget
            budgetBook.remove(index);
            size--;
        }
    }

}
