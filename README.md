# Personal Finance Manager

The Personal Finance Manager is a Java desktop application that allows users to manage their personal finances efficiently. It provides a graphical user interface (GUI) for users to add, view, and categorize their income and expense transactions. The application also offers a summary of the user's financial activities, including total income, total expenses, and net balance.

## Features

- **Add Transactions**: Users can add new transactions by providing the description, amount, type (income or expense), and category.
- **View Transactions**: Users can view a list of all recorded transactions along with their details.
- **Transaction Summary**: The application provides a summary of total income, total expenses, and net balance, helping users track their financial health.
- **Persistent Storage**: Transactions are stored in a text file (`transactions.txt`), ensuring data persistence between sessions.

## Technologies Used

- Java Swing: Graphical user interface development.
- Java I/O: File handling for data persistence.
- Object-Oriented Programming (OOP): Modular and structured code design.

## How to Run

1. **Requirements**:
   - Java Development Kit (JDK) installed on your system.
   - Integrated Development Environment (IDE) like Visual Studio Code, Eclipse, or IntelliJ IDEA.

2. **Setup Instructions**:
   - Download or clone the project repository.
   - Open the project in your IDE.
   - Compile the Java files (`FinanceManager.java` and `Transaction.java`).
   - Run the `FinanceManager` class to start the application.

## Usage

1. **Adding a Transaction**:
   - Enter the transaction details (description, amount, type, category) in the input fields.
   - Click the "Add Transaction" button to save the transaction.

2. **Viewing Transactions**:
   - Click the "View Transactions" button to display all recorded transactions in the text area.

3. **Summary**:
   - The summary of total income, total expenses, and net balance is displayed along with the transactions.

## Project Structure

- **FinanceManager.java**: Main class handling the user interface, event handling, and interaction with `Transaction` class and file operations.
- **Transaction.java**: Represents a single financial transaction with attributes for description, amount, type, and category.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
