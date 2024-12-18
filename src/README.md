# ExpenseTracker
Expense tracker using CLI

Lint to the project - https://roadmap.sh/projects/expense-tracker

This project helps one to save and track the expenses in JSON file.

## The operations supported:

1. Add Expenses
2. List Expenses
3. Get the summary of the total amount of all the expenses
4. Delete an expense
5. Get the summary of total amount of expenses in a particular month

## How to run the program

1. Pull the project to local
2. Run the below command -

   mvn clean compile
3. Command to add expense - 

mvn exec:java -Dexec.mainClass="Project.Main" -Dexec.args="add --description Lunch --amount 20"

4. Command to list expenses

mvn exec:java -Dexec.mainClass="Project.Main" -Dexec.args="list"

5. Command to get the summary of the total amount of all the expenses - 


mvn exec:java -Dexec.mainClass="Project.Main" -Dexec.args="summary"

6. Command to delete an expense

mvn exec:java -Dexec.mainClass="Project.Main" -Dexec.args="delete --id 2"

7. Command to get the summary of total amount of expenses in a particular month

mvn exec:java -Dexec.mainClass="Project.Main" -Dexec.args="summary month --8"

Happy coding!!!



