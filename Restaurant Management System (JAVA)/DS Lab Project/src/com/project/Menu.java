package com.project;

import java.io.*;

public class Menu {
    public Menu() throws IOException {
        readFile();
        meals = generateMenu();
        todayMenu();
    }
    private LinkedList<String> starter = new LinkedList<>();
    private LinkedList<String> mainCourse = new LinkedList<>();
    private LinkedList<String> desert = new LinkedList<>();
    private LinkedList<LinkedList<String>> meals = new LinkedList<LinkedList<String>>();
    private LinkedList<LinkedList<String>> todayMeal = new LinkedList<LinkedList<String>>();

    public LinkedList<LinkedList<String>> getTodayMeal() {
        return todayMeal;
    }
    private void readFile() {
        try {
            String filename1 = "C:/Users/ammar/OneDrive/Desktop/item1.txt";
            File file1 = new File(filename1);
            FileReader fr1 = new FileReader(file1);
            BufferedReader br1 = new BufferedReader(fr1);
            String line1;
            while ((line1 = br1.readLine()) != null) {
                starter.add(line1);
            }
            br1.close();
            fr1.close();

            String filename2 = "C:/Users/ammar/OneDrive/Desktop/item2.txt";
            File file2 = new File(filename2);
            FileReader fr2 = new FileReader(file2);
            BufferedReader br2 = new BufferedReader(fr2);
            String line2;
            while ((line2 = br2.readLine()) != null) {
                mainCourse.add(line2);
            }
            br2.close();
            fr2.close();

            String filename3 = "C:/Users/ammar/OneDrive/Desktop/item3.txt";
            File file3 = new File(filename3);
            FileReader fr3 = new FileReader(file3);
            BufferedReader br3 = new BufferedReader(fr3);
            String line3;
            while ((line3 = br3.readLine()) != null) {
                desert.add(line3);
            }
            br3.close();
            fr3.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private LinkedList<LinkedList<String>> generateMenu() {
        LinkedList<LinkedList<String>> combo = new LinkedList<>();
        backtracking(starter, mainCourse, desert, new LinkedList<>(), 0,combo);
        return combo;
    }

    private void backtracking(LinkedList<String> starter, LinkedList<String> mainCourse, LinkedList<String> dessert, LinkedList<String> currentCombo, int category, LinkedList<LinkedList<String>> result) {
        if (currentCombo.size() == 3) {
            result.add(new LinkedList<>(currentCombo));
            return;
        }

        LinkedList<String> currentCategory = null;
        switch (category) {
            case 0:
                currentCategory = starter;
                break;
            case 1:
                currentCategory = mainCourse;
                break;
            case 2:
                currentCategory = dessert;
                break;
        }

        for (int i = 0; i < currentCategory.size(); i++) {
            currentCombo.add(currentCategory.get(i));
            backtracking(starter, mainCourse, dessert, currentCombo, category + 1, result);
            currentCombo.remove(currentCombo.size()-1); 
        }
    }
    private void todayMenu(){
        for(int i =0;i<20;i++){
            int n = (int) (Math.random() * ((meals.size()-1)+0) +0);
            todayMeal.add(meals.get(n));
        }

    }
    public void displayMenu(){
        for(int i =0;i<todayMeal.size();i++){
            System.out.println((i+1)+". "+todayMeal.get(i));
        }
    }


}
