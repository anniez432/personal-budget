import java.sql.Date;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        //take in user input
        Scanner input = new Scanner(System.in);

        boolean validInput = false;
        BudgetBook budgetBook = null;
        while(!validInput){
            System.out.println("Hello! Would you like to create a BudgetBook that will hold your budgets? Please enter Y or N.");

            if (input.hasNext()) {
                String answer = input.next();
                if (answer.equalsIgnoreCase("Y")) {
                    //create the user's budgetbook
                    budgetBook = createBudgetBook(input);
                    if(budgetBook != null) {
                        validInput = true;
                    }
                } else if (answer.equalsIgnoreCase("N")){
                    System.out.println("Ok. Ending program.");
                    validInput = true;
                    input.close();
                    System.exit(0);
                } else {
                    //should hopefully clear input
                    input.nextLine();
                }
            }
        }
        //should only continue if input = Y
        validInput = false;
        //reset validInput

        if (budgetBook != null) {
            PersonalBudget personalBudget = new PersonalBudget("y", date, 70);

            if (personalBudget != null) {
                //add budget to budgetbook
                budgetBook.addBudget(personalBudget);

                while(!validInput) {
                    System.out.println("Would you like to add an expense or income to your budget? Please enter E for expense," +
                            "I for income, or N for neither: ");
                    if (input.hasNext()) {
                        String answer = input.next();
                        //add an expense
                        if (answer.equalsIgnoreCase("E")) {
                            addExpense(personalBudget, input);

                        } //add an income
                        else if (answer.equalsIgnoreCase("I")) {
                            addIncome(personalBudget, input);
                        } //neither
                        else if(answer.equalsIgnoreCase("N")){
                            System.out.println("Ok.");
                            validInput = true;
                        } else {
                            System.out.println("Invalid input. Please try again.");
                        }
                    }
                }
            }

        }



        //should only continue if a budget was created
//create new budgets

        //personalBudget = promptCreateBudget(input);

    }

    /**
     * Creates a BudgetBook based on user input.
     * @param input The Scanner taking in user input.
     * @return budgetBook The created budgetbook.
     */
    public static BudgetBook createBudgetBook(Scanner input) {
        //prompt for name
        System.out.println("Please enter a name for this book:");
        //Determine date of creation
        Date date = new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfYear());
        String name = null;
        BudgetBook budgetBook = null;
        //An invalid  name will throw a NullPointerException
        try {
            name = input.next();
            budgetBook = new BudgetBook(name, date);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            //re-prompt creation of BudgetBook if name is invalid.
            createBudgetBook(input);
        }
        System.out.println("Wonderful! You now have a budget book called: " + name);
        return budgetBook;
    }

    public static PersonalBudget createBudget(Scanner input){
        System.out.println("Please enter a name for this budget:");
        Date date = new Date(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfYear());
        String name = null;
        PersonalBudget personalBudget = null;
        int limit;

        //catch exception if name is invalid
        try {
            name = input.next();
            System.out.println("Please enter your spending limit for this budget (ex: 100): ");
            limit = input.nextInt();
            personalBudget = new PersonalBudget(name, date, limit);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
            //re-prompt creation of PersonalBudget if name is invalid.
            createBudget(input);
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            //re-prompt creation of PersonalBudget if limit is invalid.
            createBudget(input);
        }

        System.out.println("Wonderful! You now have a budget called: " + name + ". You can now add expenses and incomes to this budget.");
        return personalBudget;
    }

    public static PersonalBudget promptCreateBudget(Scanner input) {
        PersonalBudget personalBudget = null;
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Would you like to create a budget?");
            if (input.hasNext()) {
                String answer = input.next();
                if (answer.equalsIgnoreCase("Y")) {
                    //create the user's budgetbook
                    personalBudget = createBudget(input);
                    validInput = true;
                } else if (answer.equalsIgnoreCase("N")) {
                    System.out.println("Ok. Ending program.");
                    validInput = true;
                    input.close();
                    System.exit(0);
                } else {
                    //should hopefully clear input
                    input.next();
                }
            }
        }

        return personalBudget;
    }

    /**
     * Adds an expense to the specified personal budget.
     * @param budget The budget the expense should be added to.
     * @param input The Scanner object to gather user input
     */
    public static void addExpense(PersonalBudget budget, Scanner input){
        System.out.println("How much was the expense? ex: 20");
        if(input.hasNextInt()){
            int expenseAmount = input.nextInt();
            input.nextLine();
        } else {
            System.out.println("Please enter a valid amount.");
            addExpense(budget, input);
        }

        System.out.println("What is the description for the expense? ex: Bought a new shirt");
        if(input.hasNextLine()){
            String description = input.nextLine();
        } else {
            System.out.println("Please enter a valid description.");
            addExpense(budget, input);
        }
        budget.addExpense(expenseAmount, description);
        System.out.println("Your expense has been added!");
    }

    /**
     * Adds an income to the specified budget.
     * @param budget The budget the income is to be added to.
     * @param input The Scanner object to gather user input.
     */
    public static void addIncome(PersonalBudget budget, Scanner input){
        System.out.println("How much was the income? ex: 100");
        if(input.hasNextInt()){
            int incomeAmount = input.nextInt();
            input.nextLine();
        } else {
            System.out.println("Please enter a valid amount.");
            addIncome(budget, input);
        }

        System.out.println("What is the description for the income? ex: Payday");
        if(input.hasNextLine()){
            String description = input.nextLine();
        } else {
            System.out.println("Please enter a valid description.");
            addIncome(budget, input);
        }
        budget.addIncome(incomeAmount, description);
        System.out.println("Your income has been added!");
    }
}
