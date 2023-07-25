package com.example.course_work_sd2_task2;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static FoodQueue cashier1 = new FoodQueue(2);
    public static FoodQueue cashier2 = new FoodQueue(3);
    public static FoodQueue cashier3 = new FoodQueue(5);
    public static ArrayList<Customer> waitingQueue =  new ArrayList<>();
    public static int incomeCashier1 = 0;
    public static int incomeCashier2 = 0;
    public static int incomeCashier3 = 0;
    public static int burgersCount = 50;
    static String cashiers = """
                ******************
                **   Cashiers   **
                ******************
                """;

    public static void main(String[] args) {
        String question = """
                \n-----------------------------------------  Menu  -----------------------------------------
                100 or VFQ: View all Queues.
                101 or VEQ: View all Empty Queues.
                102 or ACQ: Add customer to a Queue.
                103 or RCQ: Remove a customer from a Queue.
                104 or PCQ: Remove a served customer.
                105 or VCS: View Customers Sorted in alphabetical order.
                106 or SPD: Store Program Data into file.
                107 or LPD: Load Program Data from file.
                108 or STK: View Remaining burgers Stock.
                109 or AFS: Add burgers to Stock.
                110 or IFQ: View the income of each queue.
                112 or GUI: View on GUI.
                999 or EXT: Exit the Program.
                ------------------------------------------------------------------------------------------
                """;

        boolean exit = true;
        while (exit) {
            System.out.println(question);

            Scanner input = new Scanner(System.in);
            System.out.print("Enter the number corresponding to your choice: ");
            String userInput = input.nextLine();

            if (burgersCount <= 10) {
                System.out.println("Attention! burger store low on inventory. Please restock");
            }

            switch (userInput) {
                case "100", "VFQ" -> viewAllQueues();
                case "101", "VEQ" -> viewEmptyQueues();
                case "102", "ACQ" -> addCustomer();
                case "103", "RCQ" -> {
                    if (cashier1.getCashier()[0] != null || cashier2.getCashier()[0] != null || cashier2.getCashier()[0] != null) {
                        removeCustomer();
                    } else {
                        System.out.println("Customers are not in customer queues.");
                    }
                }
                case "104", "PCQ" -> {
                    if (cashier1.getCashier()[0] != null || cashier2.getCashier()[0] != null || cashier3.getCashier()[0] != null) {
                        removeServedCustomer();
                    } else {
                        System.out.println("Customers are not in customer queues");
                    }
                }
                case "105", "VCS" -> {
                    if (cashier1.getCashier()[0] != null || cashier2.getCashier()[0] != null || cashier3.getCashier()[0] != null) {
                        viewCustomersList();
                    } else {
                        System.out.println("Customers are not in customer queues");
                    }
                }
                case "106", "SPD" -> storeDataIntoFile();
                case "107", "LPD" -> loadDataFromFile();
                case "108", "STK" -> viewRemainingBurgers();
                case "109", "AFS" -> addBurgers();
                case "110", "IFQ’" -> incomeOfBurgers();
                case "112", "GUI" -> viewGuiOfApplication();
                case "999", "EXT" -> exit = false;
                default -> System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private static void viewAllQueues() {
        System.out.print(cashiers);
        for (int i = 0; i <= 4; i++) {
            try {
                if (cashier1.getCashier()[i] != null) {
                    System.out.print("O" + "\t\t");
                } else {
                    System.out.print("X\t\t");
                }
            } catch (IndexOutOfBoundsException error) {
                System.out.print(" \t\t");
            }

            try {
                if (cashier2.getCashier()[i] != null) {
                    System.out.print("O" + "\t\t");
                } else {
                    System.out.print("X\t\t");
                }
            } catch (IndexOutOfBoundsException error) {
                System.out.print(" \t\t");
            }

            try {
                if (cashier3.getCashier()[i] != null) {
                    System.out.println("O" + "\t\t");
                } else {
                    System.out.println("X\t\t");
                }
            } catch (IndexOutOfBoundsException error) {
                System.out.println(" \t\t");
            }
        }
        System.out.println("\nO – Occupied   X – Not Occupied");
    }
    public static void viewEmptyQueues() {
        System.out.print(cashiers);
        for (int i = 0; i <= 4; i++) {
            try {
                if (cashier1.getCashier()[i] != null) {
                    System.out.print(" \t\t");
                } else {
                    System.out.print("X\t\t");
                }
            } catch (IndexOutOfBoundsException error) {
                System.out.print(" \t\t");
            }

            try {
                if (cashier2.getCashier()[i] != null) {
                    System.out.print(" \t\t");
                } else {
                    System.out.print("X\t\t");
                }
            } catch (IndexOutOfBoundsException error) {
                System.out.print(" \t\t");
            }

            try {
                if (cashier3.getCashier()[i] != null) {
                    System.out.println(" \t\t");
                } else {
                    System.out.println("X\t\t");
                }
            } catch (IndexOutOfBoundsException error) {
                System.out.println(" \t\t");
            }
        }

        System.out.println("\nX – Not Occupied\n");

        int numOfCustomerCashier1 = cashier1.countCustomer();
        if (numOfCustomerCashier1 == 2) {
            System.out.println("Attention: Cashier1 Customer Queue is currently full.");
        }

        int numOfCustomerCashier2 = cashier2.countCustomer();
        if (numOfCustomerCashier2 == 3) {
            System.out.println("Attention: Cashier2 Customer Queue is currently full.");
        }

        int numOfCustomerCashier3 = cashier3.countCustomer();
        if (numOfCustomerCashier3 == 5) {
            System.out.println("Attention: Cashier3 Customer Queue is currently full.");
        }
    }
    public static void addCustomer() {
        String firstName = nameValidation("first name");
        String secondName = nameValidation("second name");
        int burgersReq = burgersValidation();

        Customer customer = new Customer(firstName, secondName, burgersReq);

        for (int i = 0; i < 5; i++) {
            try {
                if (cashier1.getCashier()[i] == null) {
                    cashier1.getCashier()[i] = customer;
                    System.out.println("Added a customer to cashier 1 queues.");
                    return;
                }
            } catch (IndexOutOfBoundsException ignored) {}

            try {
                if (cashier2.getCashier()[i] == null) {
                    cashier2.getCashier()[i] = customer;
                    System.out.println("Added a customer to cashier 2 queues.");
                    return;
                }
            } catch (IndexOutOfBoundsException ignored) {}

            try {
                if (cashier3.getCashier()[i] == null) {
                    cashier3.getCashier()[i] = customer;
                    System.out.println("Added a customer to cashier 3 queues.");
                    return;
                }
            } catch (IndexOutOfBoundsException ignored) {}
            try {
                if (cashier1.getCashier()[1] != null && cashier2.getCashier()[2] != null && cashier3.getCashier()[4] != null) {
                    System.out.println("Attention: Cashier Customer Queues are currently full. Adding customer to waiting queues");
                    addToWaitingQueue(customer);
                    return;
                }
            } catch (IndexOutOfBoundsException ignored) {}
        }
    }
    public static void removeCustomer() {
        int queueNum = cashierQueueValidation();
        int position = queueValidation(queueNum);

        try {
            switch (queueNum) {
                case 1 -> {
                    if (cashier1.getCashier()[position - 1] != null) {
                        for (int i = 0; i < 2 - position; i++) {
                            cashier1.getCashier()[position - 1 + i] = cashier1.getCashier()[position + i];
                        }
                        cashier1.getCashier()[1] = null;
                        System.out.println("Removed " + position + " customer at cashier 1 queue");
                    } else {
                        System.out.println("No person found at position");
                    }
                }
                case 2 -> {
                    if (cashier2.getCashier()[position - 1] != null) {
                        for (int i = 0; i < 3 - position; i++) {
                            cashier2.getCashier()[position - 1 + i] = cashier2.getCashier()[position + i];
                        }
                        cashier2.getCashier()[2] = null;
                        System.out.println("Removed " + position + " customer at cashier 2 queue");
                    } else {
                        System.out.println("No person found at position");
                    }
                }
                case 3 -> {
                    if (cashier3.getCashier()[position - 1] != null) {
                        for (int i = 0; i < 5 - position; i++) {
                            cashier3.getCashier()[position - 1 + i] = cashier3.getCashier()[position + i];
                        }
                        cashier3.getCashier()[4] = null;
                        System.out.println("Removed " + position + " customer at cashier 3 queue");
                    } else {
                        System.out.println("No person found at position");
                    }
                }
            }
        } catch (InputMismatchException error) {
            System.out.println("Invalid input. Please try again.");
        }
    }
    public static void removeServedCustomer() {
        int queueNum = cashierQueueValidation();

        switch (queueNum) {
            case 1 -> {
                if (cashier1.getCashier()[0] != null) {
                    if (burgersCount >= cashier1.getCashier()[0].getBurgersReq()) {
                        burgersCount = burgersCount - cashier1.getCashier()[0].getBurgersReq();
                        incomeCashier1 = incomeCashier1 + cashier1.getCashier()[0].getBurgersReq() * 650;
                    } else {
                        System.out.println("The number of burgers required is not available.");
                        break;
                    }
                    for (int i = 0; i < 1; i++) {
                        cashier1.getCashier()[i] = cashier1.getCashier()[i + 1];
                    }
                    cashier1.getCashier()[1] = null;
                    System.out.println("Removed served customer from cashier 1 queue.");
                } else {
                    System.out.println("No person found at position");
                }
            }
            case 2 -> {
                if (cashier2.getCashier()[0] != null) {
                    if (burgersCount >= cashier2.getCashier()[0].getBurgersReq()) {
                        burgersCount = burgersCount - cashier2.getCashier()[0].getBurgersReq();
                        incomeCashier2 = incomeCashier2 + cashier2.getCashier()[0].getBurgersReq() * 650;
                    } else {
                        System.out.println("The number of burgers required is not available.");
                        break;
                    }
                    for (int i = 0; i < 1; i++) {
                        cashier2.getCashier()[i] = cashier2.getCashier()[i + 1];
                    }
                    cashier2.getCashier()[1] = null;
                    System.out.println("Removed served customer from cashier 2 queue.");
                } else {
                    System.out.println("No person found at position");
                }
            }
            case 3 -> {
                if (cashier3.getCashier()[0] != null) {
                    if (burgersCount >= cashier3.getCashier()[0].getBurgersReq()) {
                        burgersCount = burgersCount - cashier3.getCashier()[0].getBurgersReq();
                        incomeCashier3 = incomeCashier3 + cashier3.getCashier()[0].getBurgersReq() * 650;
                    } else {
                        System.out.println("The number of burgers required is not available.");
                        break;
                    }
                    for (int i = 0; i < 1; i++) {
                        cashier3.getCashier()[i] = cashier3.getCashier()[i + 1];
                    }
                    cashier3.getCashier()[1] = null;
                    System.out.println("Removed served customer from cashier 3 queue.");
                } else {
                    System.out.println("No person found at position");
                }
            }
            default -> System.out.println("Invalid input. Please try again.");
        }
    }
    public static void viewCustomersList() {
        int index = 0;
        String[] names = new String[10];

        for (Customer customer: cashier1.getCashier()) {
            if (customer != null) {
                names[index] = customer.getFirstName() + " " + customer.getSecondName();
                index++;
            }
        }
        for (Customer customer: cashier2.getCashier()) {
            if (customer != null) {
                names[index] = customer.getFirstName() + " " + customer.getSecondName();
                index++;
            }
        }
        for (Customer customer: cashier3.getCashier()) {
            if (customer != null) {
                names[index] = customer.getFirstName() + " " + customer.getSecondName();
                index++;
            }
        }

        for (int i = 0; i < index - 1; i++) {
            for (int j = 0; j < index - i - 1; j++) {
                String name1 = names[j];
                String name2 = names[j + 1];

                if (name1.compareTo(name2) > 0) {
                    names[j] = name2;
                    names[j + 1] = name1;
                }
            }
        }

        for (String name: names) {
            if (name != null) {
                System.out.println(name);
            }
        }

    }
    public static void storeDataIntoFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("w1987581.txt"))) {
            writer.write(String.format("""
                    Cashier 2
                        Customer 1 : %s
                        Customer 2 : %s
                    """, customer(1,0), customer(1,1)));
            writer.write(String.format("""
                    Cashier 2
                        Customer 1 : %s
                        Customer 2 : %s
                        Customer 3 : %s
                    """, customer(2,0), customer(2,1), customer(2,2)));
            writer.write(String.format("""
                    Cashier 2
                        Customer 1 : %s
                        Customer 2 : %s
                        Customer 3 : %s
                        Customer 4 : %s
                        Customer 5 : %s
                    """, customer(3,0), customer(3,1), customer(3,2), customer(3,3), customer(3,4)));
            writer.write("\nBurgers Stock: " + burgersCount);

            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing data to the file: " + e.getMessage());
        }
    }
    public static void loadDataFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("w1987581.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading data from the file: " + e.getMessage());
        }
    }
    public static void viewRemainingBurgers() {
        System.out.println("Burgers Stock: " + burgersCount);
    }
    public static void addBurgers() {
        Scanner input = new Scanner(System.in);
        System.out.print("New stock burgers: ");
        try {
            int newNumOfBurgers = input.nextInt();
            burgersCount += newNumOfBurgers;
            System.out.println("New Burgers Stock: " + burgersCount);
        } catch (InputMismatchException error) {
            System.out.println("Invalid input. Please try again.");
        }
    }

    public  static void incomeOfBurgers() {
        System.out.printf(("""
                    Cashier     |   Income
                    ----------------------
                    Cashier 1   |   Rs. %s.00
                    Cashier 2   |   Rs. %s.00
                    Cashier 3   |   Rs. %s.00
                """),
                incomeCashier1, incomeCashier2, incomeCashier3 );
    }

    private static void viewGuiOfApplication(){
        try {
            FoodieFaveQueueApplication.main(new String[0]);
            System.out.println("Application is closed");
        } catch (Exception error) {
            System.out.println("Exception: " + error);
        }
    }
    private static void addToWaitingQueue(Customer customer) {
        waitingQueue.add(customer);
    }

    private static String nameValidation(String nameFormat) {
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.printf("Enter customer %s: ", nameFormat);
            String name = input.nextLine();
            if (name.matches("^[a-zA-Z]+$")) {
                return name.trim();
            }
            System.out.println("Please enter correct name.");
        }
    }
    private static int burgersValidation() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter number of burgers required: ");
                int burgersReq = input.nextInt();
                if (burgersCount >= burgersReq) {
                    return burgersReq;
                } else {
                    System.out.println("The number of burgers required is not available.");
                    System.out.println("The number of burgers available is: " + burgersCount);
                }
            } catch (InputMismatchException error) {
                System.out.println("Please enter the correct number of burgers required.");
            }
        }
    }
    private static int cashierQueueValidation() {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter cashier number (1, 2, or 3): ");
                int userInput = input.nextInt();
                if (1 <= userInput && userInput <= 3) {
                    return userInput;
                } else {
                    System.out.println("Enter number should be 1 or 2 or 3. Please try again");
                }
            } catch (InputMismatchException error) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    private static int queueValidation(int cashierNo) {
        while (true) {
            try {
                Scanner input = new Scanner(System.in);
                System.out.print("Enter customer position at the queue: ");
                int position = input.nextInt();
                if (cashierNo == 1) {
                    if (1 <= position && position <= 2) {
                        return position;
                    } else {
                        System.out.println("Entered position is out of range. Please try again.");
                    }
                } else if (cashierNo == 2) {
                    if (1 <= position && position <= 3) {
                        return position;
                    } else {
                        System.out.println("Entered position is out of range. Please try again.");
                    }
                } else if (cashierNo == 3) {
                    if (1 <= position && position <= 5) {
                        return position;
                    } else {
                        System.out.println("Entered position is out of range. Please try again.");
                    }
                }
            } catch (InputMismatchException error) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }
    private static String customer (int cashier, int position) {
        switch (cashier) {
            case 1 -> {
                if (cashier1.getCashier()[position] != null) {
                    return cashier1.getCashier()[position].getFirstName() + " " + cashier1.getCashier()[position].getSecondName() + " is order " + cashier1.getCashier()[position].getBurgersReq() + " burgers.";
                } else {
                    return "No person found at position";
                }
            }
            case 2 -> {
                if (cashier2.getCashier()[position] != null) {
                    return cashier2.getCashier()[position].getFirstName() + " " + cashier2.getCashier()[position].getSecondName() + " is order " + cashier2.getCashier()[position].getBurgersReq() + " burgers.";
                } else {
                    return "No person found at position";
                }
            }
            case 3 -> {
                if (cashier3.getCashier()[position] != null) {
                    return cashier3.getCashier()[position].getFirstName() + " " + cashier3.getCashier()[position].getSecondName() + " is order " + cashier3.getCashier()[position].getBurgersReq() + " burgers.";
                } else {
                    return "No person found at position";
                }
            }default -> {
                return null;
            }
        }
    }
}
