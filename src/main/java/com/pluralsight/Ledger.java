package com.pluralsight;

import java.io.*;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {
    public static void main(String[] args) {
        System.out.println("== Welcome to your Ledger Application ==");
        run();

    }

    public static void run() {
        while (true) {
            menu();
          menuSelector();
        }
    }

    public static void menu() {
        System.out.println("What do you want to do?\n " +
                "  1-  Add Deposit \n" +
                "  2-  Make Payment (Debit) \n" +
                "  3- display the ledger screen\n" +
                "  4-  Quit the application\n");

    }

    public static void menuSelector() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.println("=== Enter your desired deposit $$$ ===");
                addDeposit();
                break;
            case 2:
                System.out.print("=== Debit only ===");
                makePayment();
                break;
            case 3:
                System.out.println("=== LEDGER Screen ===");
                ledgerScreen();
                break;
            case 4:
                scanner.close();
                System.exit(0);
                break;
            default:
                System.out.println("Incorrect option entered, try again");
        }

        System.out.println("\nPress ENTER to continue...\n");
        scanner.nextLine();

    }

   public static void addDeposit() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter description: ");
            String description = scanner.nextLine();

            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();

            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            Transaction deposit = new Transaction(description, vendor, amount);
            BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));
                writer.write(deposit.toString());

        } catch (IOException e) {
            System.out.println(e.fillInStackTrace());
        }

   }

   public static void makePayment(){
    try {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Enter description: ");
    String description = scanner.nextLine();

    System.out.print("Enter vendor: ");
    String vendor = scanner.nextLine();

    System.out.print("Enter amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine();

    Transaction payment = new Transaction(description, vendor, -amount);
    BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true));
    writer.write(payment.toString());

    } catch (IOException e) {
        System.out.println(e.fillInStackTrace());
    }
}

public static void ledgerScreen() {
    Scanner scanner = new Scanner(System.in);
    boolean keepRunning = true;

    while (keepRunning) {
        System.out.println(" 1- All - Show all entries");
        System.out.println(" 2- Deposits - Show only deposits");
        System.out.println(" 3- Payments - Show only payments");
        System.out.println(" 4- Reports - Run reports");
        System.out.println(" 5- Home - Go back to home");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("=== ALL TRANSACTIONS ===");
                displayAll();
                break;
            case 2:
                System.out.println("=== DEPOSITS ONLY ===");
                displayDeposits();
                break;
            case 3:
                System.out.println("=== PAYMENTS ONLY ===");
                displayPayments();
                break;
            case 4:
                System.out.println(" === Reports Screen === ");
                reportsScreen();
                break;
            case 5:
                keepRunning = false;
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}

    public static void displayAll() {
        ArrayList<Transaction> transactions = loadTransactions();

        // Show newest first (**reverse order**)
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            System.out.printf("%s | %s | %s | %s | $%.2f\n",
                    t.getDate(),
                    t.getTime(),
                    t.getDescription(),
                    t.getVendor(),
                    t.getAmount());
        }
    }

    public static void displayDeposits() {
        ArrayList<Transaction> transactions = loadTransactions();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);

            // Only show if amount is POSITIVE
            if (t.getAmount() > 0) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount());
            }
        }
    }

    public static void displayPayments() {
        ArrayList<Transaction> transactions = loadTransactions();

        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);

            // Only show if amount is NEGATIVE
            if (t.getAmount() < 0) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount());
            }
        }
        System.out.println("Thank you for your purchase.");
    }

    public static void reportsScreen() {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println(" 1- Month To Date");
            System.out.println(" 2- Previous Month");
            System.out.println(" 3- Year To Date");
            System.out.println(" 4- Previous Year");
            System.out.println(" 5- Search by Vendor");
            System.out.println(" 0- Home - Go back to ledger screen");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("=== Search Current month ===");
                    monthToDate();
                    break;
                case 2:
                    System.out.println("=== Search Previous months ===");
                    previousMonth();
                    break;
                case 3:
                    System.out.println("=== Search Current year ===");
                    yearToDate();
                    break;
                case 4:
                    System.out.println(" === Search Previous years === ");
                    previousYear();
                    break;
                case 5:
                    System.out.println(" === Search by Vendor === ");
                    System.out.println("Enter vendor to search: ");
                    SearchByVendor();
                    break;
                case 0:
                    System.out.println("=== LEDGER Screen ===");
                    ledgerScreen();
                    keepRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    public static void monthToDate() {
        ArrayList<Transaction> allTransactions = loadTransactions();

        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();
        int currentYear = today.getYear();

        System.out.println("Showing transactions for: " + today.getMonth() + " | " + currentYear);
        System.out.println();

        for (Transaction t : allTransactions) {
            LocalDate transactionDate = t.getDate();
            int transactionMonth = transactionDate.getMonthValue();
            int transactionYear = transactionDate.getYear();

            if (transactionMonth == currentMonth && transactionYear == currentYear) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount());
            }
        }
    }
    public static void previousMonth() {
        ArrayList<Transaction> allTransactions = loadTransactions();

        LocalDate input = LocalDate.now();
        int currentMonth = input.getMonthValue();
        int previousMonth = currentMonth -1;
        int currentYear = input.getYear();
        int previousYear = currentYear;

        if(previousMonth == 0){
            previousMonth = 12;
            previousYear = currentYear - 1;
        }
        for (Transaction t : allTransactions) {
            LocalDate transactionDate = t.getDate();
            int transactionMonth = transactionDate.getMonthValue();
            int transactionYear = transactionDate.getYear();

            if (transactionMonth == previousMonth && transactionYear == currentYear) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount());
            }
        }
    }

    public static void yearToDate() {
        ArrayList<Transaction> allTransactions = loadTransactions();

        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();


        System.out.println("Showing transactions for: " + currentYear + "\n");

        for (Transaction t : allTransactions) {
            LocalDate transactionDate = t.getDate();
            int transactionYear = transactionDate.getYear();

            if (transactionYear == currentYear) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount());
            }
        }
    }
    public static void previousYear() {
        ArrayList<Transaction> allTransactions = loadTransactions();

        LocalDate input = LocalDate.now();
        int currentYear = input.getYear();
        int previousYear = currentYear -1;

        System.out.println("Showing transactions for: " + previousYear + "\n");

        for (Transaction t : allTransactions) {
            LocalDate transactionDate = t.getDate();
            int transactionYear = transactionDate.getYear();

            if (transactionYear == previousYear) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount());
            }
        }
    }

    public static void SearchByVendor() {

    }

public static ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            BufferedReader bufreader = new BufferedReader(
                    new FileReader("transactions.csv"));

            String input;
            while ((input = bufreader.readLine()) != null) {
                // parts[0] = date
                // parts[1] = time
                // parts[2] = description
                // parts[3] = vendor
                // parts[4] = amount
                String[] parts = input.split("\\|");
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);

                Transaction t = new Transaction(description, vendor, amount);
                transactions.add(t);
            }

            bufreader.close();

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return transactions;
    }

    public static void saveTransaction (Transaction record){
        try {
            BufferedWriter bufwriter = new BufferedWriter(
                    new FileWriter("transactions.csv", true));

            // Format: date|time|description|vendor|amount
            String input = String.format("%s|%s|%s|%s|%.2f\n",
                    record.getDate(),
                    record.getTime(),
                    record.getDescription(),
                    record.getVendor(),
                    record.getAmount());

            bufwriter.write(input);
            bufwriter.close();

            System.out.println("Transaction saved!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
