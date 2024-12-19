package Project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpenseManager {

    private static final String FILE_PATH = "src/main/java/Project/expenses.json";

    private static final com.fasterxml.jackson.databind.ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    static List<Expense> Expenses = new ArrayList<>();

    static {
        try {
            loadExpensesFromFile();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
    }

    public static void loadExpensesFromFile() throws IOException {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            Expense[] expensesArray = objectMapper.readValue(file, Expense[].class);
            Expenses = new ArrayList<>(Arrays.asList(expensesArray));
        } else {
            file.createNewFile();
            System.out.println("File not found. Creating new file " + FILE_PATH);
        }
    }

    public static void addExpense(String description, int amount, String category) {

        int id = Expenses.size() + 1;

        Expense expense = new Expense(id, description, amount, category);

        Expenses.add(expense);

        int totalAmount = 0;

        for (Expense exp: Expenses) {
            totalAmount += exp.getAmount();
        }

        if (totalAmount > 300) {
            System.out.println("!!!Exceeded the budget for the month!!!");
        }

        try {
            objectMapper.writeValue(new File(FILE_PATH), Expenses);
            System.out.println("Added expense successfully" + " " + "("+"ID: " +id+")");
        } catch (IOException e) {
            System.out.println("Error saving expense");
        }
    }

    public static void listAllExpenses() {

        for (Expense expense: Expenses) {
            System.out.println(expense);
        }

    }

    public static void listExpensesBasedonType(String expenseCategory) {

       long count =  Expenses.stream()
                .filter(expense -> expenseCategory.equals(expense.getCategory()))
                .peek(System.out::println)
               .count();

            if (count == 0) {
                System.out.println("No expenses found that is matching the provided category");
            }

    }

    public static void deleteExpense(int id) throws IOException {

        Expense expenseDelete = null;

        for (Expense expense: Expenses) {
            if (expense.getID() == id) {
                expenseDelete = expense;
            }
        }

        if (expenseDelete != null) {
            Expenses.remove(expenseDelete);
            objectMapper.writeValue(new File(FILE_PATH), Expenses);
            System.out.println("Deleted expense successfully");
        } else {
            System.out.println("Failed to delete expense, please enter an existing expense ID");
        }
    }

    public static void expenseSummary() {

        int totalExpense = 0;

        for (Expense expense: Expenses) {
            totalExpense += expense.getAmount();
        }

        System.out.println("Total expenses:" + " " + "$"+totalExpense);
    }

    public static void monthlyExpenseSummary(int month) {

        int monthlyTotal = 0;
        Month monthEnum = Month.of(month);

        for(Expense expense: Expenses) {

            if(expense.getDate().getMonthValue() == month) {
                monthlyTotal += expense.getAmount();
            }
        }
        System.out.println("Total expenses for " + monthEnum + ":" + " $"+ monthlyTotal);

    }

    public static void exportToFile(String file_name) {
        try(PrintWriter writer = new PrintWriter(new FileWriter(file_name))) {

            writer.println("ID,Date,Description,Amount,Category");

            for (Expense expense: Expenses) {
                writer.printf("%d,%s,%s,%d,%s%n",
                        expense.getID(),
                        expense.getDate(),
                        expense.getDescription(),
                        expense.getAmount(),
                        expense.getCategory());

            }

            System.out.println("Expenses successfully exported to " + file_name);

        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
