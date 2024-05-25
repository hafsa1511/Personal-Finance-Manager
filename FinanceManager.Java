import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FinanceManager extends JFrame {
    private static final String FILE_NAME = "transactions.txt";
    private List<Transaction> transactions;

    private JTextField descriptionField, amountField;
    private JComboBox<String> typeComboBox, categoryComboBox;
    private JTextArea displayArea;

    public FinanceManager() {
        setTitle("Personal Finance Manager");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        transactions = new ArrayList<>();
        loadTransactions();

        // GUI Components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);

        inputPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        inputPanel.add(amountField);

        inputPanel.add(new JLabel("Type:"));
        typeComboBox = new JComboBox<>(new String[]{"Income", "Expense"});
        inputPanel.add(typeComboBox);

        inputPanel.add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>(new String[]{"Salary", "Food", "Entertainment", "Utilities", "Others"});
        inputPanel.add(categoryComboBox);

        JButton addButton = new JButton("Add Transaction");
        inputPanel.add(addButton);
        addButton.addActionListener(e -> addTransaction());

        JButton viewButton = new JButton("View Transactions");
        inputPanel.add(viewButton);
        viewButton.addActionListener(e -> viewTransactions());

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void addTransaction() {
        String description = descriptionField.getText();
        double amount;
        try {
            amount = Double.parseDouble(amountField.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid amount.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String type = (String) typeComboBox.getSelectedItem();
        String category = (String) categoryComboBox.getSelectedItem();

        Transaction transaction = new Transaction(description, amount, type, category);
        transactions.add(transaction);
        saveTransactions();
        clearFields();
    }

    private void clearFields() {
        descriptionField.setText("");
        amountField.setText("");
        typeComboBox.setSelectedIndex(0);
        categoryComboBox.setSelectedIndex(0);
    }

    private void viewTransactions() {
        StringBuilder sb = new StringBuilder();
        double totalIncome = 0;
        double totalExpense = 0;

        for (Transaction transaction : transactions) {
            sb.append(transaction).append("\n");
            if (transaction.getType().equals("Income")) {
                totalIncome += transaction.getAmount();
            } else {
                totalExpense += transaction.getAmount();
            }
        }

        sb.append("\nTotal Income: ").append(totalIncome);
        sb.append("\nTotal Expense: ").append(totalExpense);
        sb.append("\nNet Balance: ").append(totalIncome - totalExpense);

        displayArea.setText(sb.toString());
    }

    private void loadTransactions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 4);
                if (parts.length == 4) {
                    String description = parts[0].trim();
                    double amount = Double.parseDouble(parts[1].trim());
                    String type = parts[2].trim();
                    String category = parts[3].trim();
                    transactions.add(new Transaction(description, amount, type, category));
                }
            }
        } catch (IOException e) {
            System.out.println("No previous transactions found.");
        }
    }

    private void saveTransactions() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Transaction transaction : transactions) {
                writer.println(transaction.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Failed to save transactions.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FinanceManager().setVisible(true));
    }
}
