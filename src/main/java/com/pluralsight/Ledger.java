package com.pluralsight;

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
}
