package com.pluralsight;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private LocalDate date;
    private LocalTime time;
    private String description;
    private String vendor;
    private double amount;

    public Transaction(String description, String vendor, double amount) {
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }
    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        return "Application:\n" +
                "Date| " + date.format(dateFormat) + "\n" +
                "Time| " + time.format(timeFormat) + "\n" +
                "Description| " + description + "\n" +
                "Vendor| " + vendor + "\n" +
                "Amount| $" + String.format("%.2f", amount) + "\n";
    }

    public void saveToFile(String fileName) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        try (FileWriter writer = new FileWriter("transactions.csv", true)) {
            writer.write("Date: " + date.format(dateFormat) + "\n");
            writer.write("Time: " + time.format(timeFormat) + "\n");
            writer.write("Description: " + description + "\n");
            writer.write("Vendor: " + vendor + "\n");
            writer.write(String.format("Amount: $%.2f%n", amount));
            writer.write("-----------------------------\n");
            System.out.println("Record saved to: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
}
