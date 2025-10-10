package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
                System.out.println();
                addDeposit();
                break;
            case 2:
                System.out.print("Debit only");
                makePayment();
                break;
            case 3:
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
        System.out.println("=== LEDGER Screen ===");
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
                //displayAll();
                break;
            case 2:
                //displayDeposits();
                break;
            case 3:
                // displayPayments();
                break;
            case 4:
                System.out.println("Reports Screen: ");
                // reportsScreen();

                break;
            case 5:
                keepRunning = false;
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
}
    public static void saveTransaction (Transaction record){
        try {
            BufferedWriter writer = new BufferedWriter(
                    new FileWriter("transactions.csv", true));

            // Format: date|time|description|vendor|amount
            String line = String.format("%s|%s|%s|%s|%.2f\n",
                    record.getDate(),
                    record.getTime(),
                    record.getDescription(),
                    record.getVendor(),
                    record.getAmount());

            writer.write(line);
            writer.close();

            System.out.println("Transaction saved!");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
