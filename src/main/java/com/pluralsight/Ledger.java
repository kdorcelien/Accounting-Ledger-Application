package com.pluralsight;

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
        // ArrayList<Product> inventory = getInventory();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                System.out.println();
                //addDeposit();
                break;
            case 2:
                System.out.print("Debit only");
                //makePayment();
                break;
            case 3:
                //ledgerScreen();
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
   //



}
