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
                    input.next();
                }
            }
        }
        //should only continue if input = Y

        //reset validInput

        validInput = false;
        PersonalBudget personalBudget = promptCreateBudget(input);

        if (personalBudget != null) {
            //add budget to budgetbook
            budgetBook.addBudget(personalBudget);
            while(!validInput) {
                System.out.println("Would you like to add an expense or income to your budget? Please enter E for expense," +
                        "I for income, or N for neither: ");
                if (input.hasNext()) {
                    String answer = input.next();
                    if (answer.equalsIgnoreCase("E")) {
                        validInput = true;
                    } else if (answer.equalsIgnoreCase("I")) {
                        validInput = true;
                    } else {
                        validInput = true;
                        System.out.println("Ok.");
                    }
                }
            }
        }


        //should only continue if a budget was created
//create new budgets

        personalBudget = promptCreateBudget(input);

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
}
