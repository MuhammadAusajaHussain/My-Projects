package com.project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

public class Restaurant implements Serializable {
    admin admin = new admin();
    Scanner scanner = new Scanner(System.in);
    Queue<Customer> q = new Queue<>();
    LinkedList<Integer> atTable = new LinkedList<>();
    HashMap<Integer,Customer> table = new HashMap<>();
    Menu menu = new Menu();
    int counterCustomer=0;
    int counterOrder=0;

    public Restaurant() throws IOException {
    }

    public void newCustomer(){
        System.out.println("Enter Name :");
        String name = scanner.next();
        System.out.println("Select Meal Number :");
        menu.displayMenu();
        int choice = scanner.nextInt();
        System.out.println("Enter Quantity :");
        int quantity = scanner.nextInt();

        Customer customer = new Customer(name,counterCustomer,choice,counterOrder,quantity);

        String filePath ="C:/Users/ammar/OneDrive/Desktop/customer.txt";
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            outputStream.
                    writeObject(customer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Customer joined line to get food");
        q.enqueue(customer);
        counterCustomer++;
        counterOrder++;
    }

    public void serveOrder(){
        Customer customer = q.dequeue();
        if (customer ==null){
            System.out.println("No one in line to get food");
        }else {
            table.put(customer.getCustomerId(), customer);
            atTable.add(customer.getCustomerId());
            System.out.println("The Customer with ID " + customer.getCustomerId() + " has been served");
        }
    }
    public void removeCustomer(){
        int index = (int) (Math.random() * (atTable.size()) + 0);
        if (index< atTable.size()) {
            int customerid = atTable.get(index);
            Customer cus = table.get(customerid);
            double bill = admin.generateBill(cus.getQuantity());
            System.out.println("Your bill is " + bill);
            admin.ispaymentSuccess(bill);
            atTable.remove(customerid);
            table.remove(customerid);
            System.out.println("Customer ID " + customerid + " has left");
        }else {
            System.out.println("No Customer to remove from tables");
        }
    }
}
