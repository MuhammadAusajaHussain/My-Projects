package com.project;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Main implements Serializable {
    public static void main(String[] args)  throws IOException {
        Scanner scanner = new Scanner(System.in);
        Restaurant restaurant = new Restaurant();

        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.print("Enter admin username: ");
            String username = scanner.nextLine();

            System.out.print("Enter admin password: ");
            String password = scanner.nextLine();


            if (username.equals("admin") && password.equals("admin")) {
                System.out.println("Admin login successful!\n");
                loggedIn = true;
            } else {
                System.out.println("Invalid username or password. Try again.\n");
            }
        }

        boolean exit = false;
        while (!exit) {
            System.out.println("*************************************************");
            System.out.println("Welcome to the Restaurant Management System");
            System.out.println("1. New Customer");
            System.out.println("2. Serve Order");
            System.out.println("3. Remove Customer");
            System.out.println("4. Display Today's Menu");
            System.out.println("5. Add item to record");
            System.out.println("6. Remove item from record");
            System.out.println("7. Exit");
            System.out.println("*************************************************");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    restaurant.newCustomer();
                    break;
                case 2:
                    restaurant.serveOrder();
                    break;
                case 3:
                    restaurant.removeCustomer();
                    break;
                case 4:
                    restaurant.menu.displayMenu();
                    break;
                case 5:
                    restaurant.admin.addItem();
                    break;
                case 6:
                    restaurant.admin.deleteItem();
                    break;
                case 7:
                    restaurant.admin.incomeRecord(restaurant.admin.getEarnings());
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
        System.out.println("Thank you for using the Restaurant Management System. Goodbye!");
    }
}
