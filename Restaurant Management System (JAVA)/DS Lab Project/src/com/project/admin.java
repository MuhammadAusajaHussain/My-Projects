package com.project;
import java.io.*;
import java.util.Scanner;

public class admin {
    HashMap<String,String> finder = new HashMap<>();
    private double earnings;
    Scanner scanner = new Scanner(System.in);
    private double mealFixPrice = 1200;

    public double getEarnings() {
       return earnings;
    }

    private void appendFile(String item, String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath,true))) {
            writer.newLine();
            writer.append(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeFile(String item,String filePath){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public LinkedList<String> readFile(String filePath) {
        LinkedList<String> linkedList = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                linkedList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return linkedList;
    }

    public void addItem(){
        System.out.println("Enter Item name");
        String item = scanner.next();
        System.out.println("Enter type (stater (0),mainCourse (1),Desert (3)");
        int choice = scanner.nextInt();
        switch (choice){
            case 0:
                appendFile(item,"C:/Users/ammar/OneDrive/Desktop/item1.txt");
                break;
            case 1:
                appendFile(item,"C:/Users/ammar/OneDrive/Desktop/item2.txt");
                break;
            case 2:
                appendFile(item,"C:/Users/ammar/OneDrive/Desktop/item3.txt");
                break;
            default:
                System.out.println("Incorrect choice");
                break;
        }
    }
    public void deleteItem(){
        System.out.println("Enter item to remove");
        String item = scanner.next();
        System.out.println("Enter type (stater (0),mainCourse (1),Desert (3)");
        int choice = scanner.nextInt();
        LinkedList<String> list = null;
        switch (choice){
            case 0:
                list = readFile("C:/Users/ammar/OneDrive/Desktop/item1.txt");
                break;
            case 1:
                list = readFile("C:/Users/ammar/OneDrive/Desktop/item2.txt");
                break;
            case 2:
                list = readFile("C:/Users/ammar/OneDrive/Desktop/item3.txt");
                break;
            default:
                System.out.println("Incorrect choice");
                break;
        }

        for (int i=0;i<list.size();i++){
            finder.put(list.get(i), list.get(i));
        }

        if (finder.containsKey(item)){
            finder.remove(item);
           list.remove(item);
            for (int i=0;i<finder.size();i++){
                switch (choice) {
                    case 0:
                        if (i < 1) {
                            writeFile(list.get(i), "C:/Users/ammar/OneDrive/Desktop/item1.txt");
                        } else{
                            appendFile(finder.remove(list.get(i)), "C:/Users/ammar/OneDrive/Desktop/item1.txt");
                        }
                        break;
                    case 1:
                        if (i<1){
                            writeFile(list.get(i), "C:/Users/ammar/OneDrive/Desktop/item1.txt");
                        }else {
                            appendFile(finder.remove(list.get(i)), "C:/Users/ammar/OneDrive/Desktop/item2.txt");
                        }
                        break;
                    case 2:
                        if (i<1){
                            writeFile(list.get(i), "C:/Users/ammar/OneDrive/Desktop/item1.txt");
                        }else {
                        appendFile(finder.remove(list.get(i)),"C:/Users/ammar/OneDrive/Desktop/item3.txt");
                        }
                        break;
                    default:
                        System.out.println("Incorrect choice");
                        break;
                }
            }
        }
        else {
            System.out.println("Item not in the file");
        }
    }
    public double generateBill(int quantity){
        return quantity*mealFixPrice;
    }
    public void ispaymentSuccess(double bill) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Did you pay (yes/no)");
        String input = scanner.next();

        while (!input.equalsIgnoreCase("yes")) {
            System.out.println("Please pay");
            System.out.println("Did you pay (yes/no)");
            input = scanner.next();
        }

        System.out.println("Thank You! Please visit again");
        earnings += bill;
    }

    public void incomeRecord(double earnings){
        String val =String.valueOf(earnings);
        appendFile("Day","C:/Users/ammar/OneDrive/Desktop/dailyearning.txt");
        appendFile(val,"C:/Users/ammar/OneDrive/Desktop/dailyearning.txt");
    }

}
