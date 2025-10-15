package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║          Welcome               ║");
        System.out.println("║  To your: Ledger Application   ║");
        System.out.println("╚════════════════════════════════╝");
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
                System.out.println("=== Enter your desired deposit $$$ ===\n");
                addDeposit();
                break;
            case 2:
                System.out.print("=== Debit only ===\n");
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
        Scanner scanner = new Scanner(System.in);
        LocalDate date;
        LocalTime time;

        System.out.print("Use current date and time? (Y/N): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("n")) {
            while (true) {
                System.out.print("Enter date (MM-dd-yyyy) or press ENTER to skip: ");
                String dateInput = scanner.nextLine().trim();

                if (dateInput.isEmpty()) {
                    date = LocalDate.now();
                    break;
                }
                try {
                    date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format! Please use MM-dd-yyyy (e.g., 10-14-2025).");
                }
            }
            while (true) {
                System.out.print("Enter time (HH:mm:ss) or press ENTER to skip: ");
                String timeInput = scanner.nextLine().trim();

                if (timeInput.isEmpty()) {
                    time = LocalTime.now();
                    break;
                }
                try {
                    time = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm:ss"));
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid time format! Please use HH:mm:ss (e.g., 14:30:00).");
                }
            }

        } else {
            date = LocalDate.now();
            time = LocalTime.now();
        }

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Transaction deposit = new Transaction(date, time, description, vendor, amount);
        saveTransaction(deposit);
    }


    public static void makePayment() {
        Scanner scanner = new Scanner(System.in);
        LocalDate date;
        LocalTime time;

        System.out.print("Use current date and time? (Y/N): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("n")) {
            while (true) {
                System.out.print("Enter date (MM-dd-yyyy): ");
                String dateInput = scanner.nextLine().trim();

                if (dateInput.isEmpty()) {
                    date = LocalDate.now();
                    break;
                }
                try {
                    date = LocalDate.parse(dateInput, DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date format! Please use MM-dd-yyyy (e.g., 10-14-2025).");
                }
            }

            while (true) {
                System.out.print("Enter time (HH:mm:ss): ");
                String timeInput = scanner.nextLine().trim();

                if (timeInput.isEmpty()) {
                    time = LocalTime.now();
                    break;
                }
                try {
                    time = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("HH:mm:ss"));
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid time format! Please use HH:mm:ss (e.g., 14:30:00).");
                }
            }

        } else {
            date = LocalDate.now();
            time = LocalTime.now();
        }

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        System.out.print("Enter vendor: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Transaction payment = new Transaction(date, time, description, vendor, amount);
        saveTransaction(payment);
    }

    public static void ledgerScreen() {
    Scanner scanner = new Scanner(System.in);
    boolean keepRunning = true;

    while (keepRunning) {
        System.out.println(" 1- All - Show all entries");
        System.out.println(" 2- Deposits - Show only deposits");
        System.out.println(" 3- Payments - Show only payments");
        System.out.println(" 4- Reports - Run reports ");
        System.out.println(" 5- Home - Go back to home ");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.println("=== ALL TRANSACTIONS ===\n");
                displayAll();
                break;
            case 2:
                System.out.println("=== DEPOSITS ONLY === \n");
                displayDeposits();
                break;
            case 3:
                System.out.println("=== PAYMENTS ONLY ===\n");
                displayPayments();
                break;
            case 4:
                System.out.println(" === Reports Screen === \n");
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

            // Only show if amount is POSITIVE(money added)
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

            // Only show if amount is NEGATIVE(money payed)
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
            System.out.println(" 6- Custom Search");
            System.out.println(" 0- Home - Go back to ledger screen");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("=== Search Current month ===\n");
                    monthToDate();
                    break;
                case 2:
                    System.out.println("=== Search Previous months ===\n");
                    previousMonth();
                    break;
                case 3:
                    System.out.println("=== Search Current year ===\n");
                    yearToDate();
                    break;
                case 4:
                    System.out.println(" === Search Previous years === \n");
                    previousYear();
                    break;
                case 5:
                    System.out.println(" === Search by Vendor === \n");
                    System.out.println("Enter vendor to search: ");
                    SearchByVendor();
                    break;
                case 6:
                    System.out.println(" === Custom Search === \n");
                     customSearch();
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
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();

        for (Transaction t : allTransactions) {
            int transactionMonth = t.getDate().getMonthValue();
            int transactionYear = t.getDate().getYear();

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

        int currentMonth = LocalDate.now().getMonthValue();
        int previousMonth = currentMonth -1;
        int currentYear = LocalDate.now().getYear();
        int previousYear = currentYear;

        if(previousMonth == 0){
            previousMonth = 12;
            previousYear = currentYear - 1;
        }
        for (Transaction t : allTransactions) {
            int transactionMonth = t.getDate().getMonthValue();
            int transactionYear = t.getDate().getYear();

            if (transactionMonth == previousMonth && transactionYear == previousYear) {
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
        int currentYear = LocalDate.now().getYear();

        for (Transaction t : allTransactions) {

            if (t.getDate().getYear() == currentYear) {
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

        int previousYear = LocalDate.now().getYear() - 1;

        for (Transaction t : allTransactions) {
            if (t.getDate().getYear() == previousYear) {
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
        ArrayList<Transaction> allTransactions = loadTransactions();
        Scanner scanner = new Scanner(System.in);
        String vendor = scanner.nextLine();

        for (Transaction t : allTransactions) {

            if (t.getVendor().trim().equalsIgnoreCase(vendor)) {
                System.out.printf("%s | %s | %s | %s | $%.2f\n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount());
            }
        }
    }
    public static void customSearch() {
        ArrayList<Transaction> allTransactions = loadTransactions();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Start Date (MM-dd-yyyy) or press ENTER to skip: ");
        String startDate = scanner.nextLine();
        LocalDate parseStartDate = null;
        if (!startDate.isEmpty()) {
            parseStartDate = LocalDate.parse(startDate);
        }

        System.out.print("Enter End Date (MM-dd-yyyy) or press ENTER to skip: ");
        String endDate = scanner.nextLine();
        LocalDate parseEndDate = null;
        if (!endDate.isEmpty()) {
            parseEndDate = LocalDate.parse(endDate);
        }

        System.out.print("Enter Description or press ENTER to skip: ");
        String description = scanner.nextLine();

        System.out.print("Enter Vendor or press ENTER to skip: ");
        String vendor = scanner.nextLine();

        System.out.print("Enter Amount or press ENTER to skip: ");
        String amount = scanner.nextLine();
        Double parseAmount = null;
        if (!amount.isEmpty()) {
            parseAmount = Double.parseDouble(amount);
        }

        for (Transaction t : allTransactions){
            boolean matches = true;
            if (parseStartDate != null) {
                if (t.getDate().isBefore(parseStartDate)) {
                    matches = false;
                }
            }
            if (parseEndDate != null) {
                if (t.getDate().isAfter(parseEndDate)) {
                    matches = false;
                }
            }
            if (!description.isEmpty()) {
                if (!t.getDescription().equalsIgnoreCase(description)) {
                    matches = false;
                }
            }
            if (!vendor.isEmpty()) {
                if (!t.getVendor().equalsIgnoreCase(vendor)) {
                    matches = false;
                }
            }
            if (parseAmount != null) {
                if (t.getAmount() != parseAmount) {
                    matches = false;
                }
            }
            if (matches){
                System.out.printf("%s | %s | %s | %s | $%.2f\n",
                        t.getDate(),
                        t.getTime(),
                        t.getDescription(),
                        t.getVendor(),
                        t.getAmount());
            }
        }
    }

public static ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();

        try {
            BufferedReader bufreader = new BufferedReader(
                    new FileReader("src/main/resources/transactions.csv"));

            bufreader.readLine();

            String input;
            while ((input = bufreader.readLine()) != null) {
                String[] parts = input.split("\\|");
                LocalDate date = LocalDate.parse(parts[0], DateTimeFormatter.ofPattern("MM-dd-yyyy"));
                LocalTime time = LocalTime.parse(parts[1], DateTimeFormatter.ofPattern("HH:mm:ss"));
                String description = parts[2];
                String vendor = parts[3];
                double amount = Double.parseDouble(parts[4]);
                Transaction t = new Transaction(date, time, description, vendor, amount);
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
                    new FileWriter("src/main/resources/transactions.csv", true));
            bufwriter.write(record.toString());
            bufwriter.close();

            System.out.println("Transaction saved!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
