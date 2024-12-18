package Project;

import java.io.IOException;
import java.util.Scanner;

import static Project.ExpenseManager.*;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner input = new Scanner(System.in);

        if (args.length == 0) {
            System.out.println("Enter proper command");
            return;
        }

        String command = args[0];

        switch (command) {
            case "add":

                if (args.length < 5) {
                    System.out.println("Enter the Descriptin and Amount for the expense");
                    break;
                }

                String description = args[2];

                int amount = Integer.parseInt(args[4]);

                if (amount < 0) {
                    System.out.println("Please enter a valid amount");
                    break;
                }

                addExpense(description, amount);

                break;

            case "list":

                if (args.length < 1){
                    System.out.println("Enter proper command");
                    break;
                }

                listAllExpenses();
                break;

            case "delete":

                if (args.length < 3){
                    System.out.println("Enter the ID number of task to be deleted");
                }

                int id = Integer.parseInt(args[2]);

                deleteExpense(id);
                break;

            case "summary":

                if (args.length < 1){
                    System.out.println("Enter proper command");
                    break;
                } else if (args.length == 1) {
                    expenseSummary();
                    break;
                } else if (args.length == 3) {
                    int month = Integer.parseInt(args[2]);

                    if (month > 0 && month < 13) {
                        monthlyExpenseSummary(month);
                    } else {
                        System.out.println("Please enter a valid month from 1 to 12");
                        break;
                    }
                }

                break;

            default:
                System.out.println("Enter valid operation");
                break;

        }

    }
}