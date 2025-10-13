=== Financial Ledger Application ===

A command-line financial tracking application built with Java that allows users to manage deposits, payments, and generate financial reports.

=== Project Description ===

This Financial Ledger Application enables users to track all financial transactions for personal or business use. All transactions are stored in a CSV file and can be filtered and searched using various criteria. The application provides an intuitive menu-driven interface for managing finances efficiently.

 Features

=== Core Functionality ===
- Add Deposits** - Record incoming money with description, vendor, and amount
- Make Payments** - Track expenses and outgoing payments
- View Ledger** - Display all transactions, deposits only, or payments only
- Generate Reports** - Multiple pre-defined report options

== Report Options ===
1. Month To Date** - View all transactions from the current month
2. Previous Month** - View transactions from last month
3. Year To Date** - View all transactions from current year
4. Previous Year** - View all transactions from previous year
5. Search by Vendor** - Find all transactions from a specific vendor
6. Custom Search** - Advanced filtering by date range, description, vendor, and amount

=== Data Management ===
- All transactions saved to CSV file ("transactions.csv")
- Persistent storage - data survives between sessions
- Automatic date/time stamping for each transaction

=== Application Screenshots ===
![Image 10-13-25 at 2 35 PM](https://github.com/user-attachments/assets/ded9ecef-ee1b-45aa-8166-4986e45b8356)
![Image 10-13-25 at 4 16 PM](https://github.com/user-attachments/assets/9be7bc17-2267-4ccc-ba1d-279436783920)

=== Home Screen ===
== Welcome to your Ledger Application ==
What do you want to do?
1-  Add Deposit
2-  Make Payment (Debit)
3- display the ledger screen
4-  Quit the application

=== LEDGER Screen ===
1- All - Show all entries
2- Deposits - Show only deposits
3- Payments - Show only payments
4- Reports - Run reports
5- Home - Go back to home

=== Interesting Code Feature ===
=== Custom Search with Flexible Filtering ===
One of the most powerful features is the custom search functionality that allows users to filter transactions by multiple criteria simultaneously.
The key innovation is that users can skip any filter they don't want to use:
Uses a boolean matches flag that starts as true
Each filter only runs if the user provided that search criterion
If ANY filter fails, matches becomes false
Transaction is only displayed if it passes ALL active filters
This creates a flexible AND-based search system.

 === Technologies Used ===

Java 17 - Programming language
File I/O - BufferedReader/BufferedWriter for CSV operations
LocalDate/LocalTime - Date and time handling
ArrayList - Dynamic transaction storage
Scanner - User input handling
Claude.ai
chatgpt.com/Obi-Java Kenobi

 === What I Learned ===

File I/O operations in Java
Working with CSV data formats
Date/time manipulation with LocalDate and LocalTime
Building interactive console applications
Data filtering and search algorithms
Object-oriented design principles




