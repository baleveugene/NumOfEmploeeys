package com.touchsoft.numberofemployees;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NumOfEmployees {

    public static void main(String[] args) {
        String pathToFile = getPathToFile();
        ArrayList<String> timingOfEmployeesList = readFromFile(pathToFile);
        int numOfEmploeeys = getNumOfEmploeeys(timingOfEmployeesList);
        System.out.println("Наибольшее количество работников в офисе: " + numOfEmploeeys);
    }

    public static String getPathToFile() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите путь к файлу: ");
        String pathToFile = in.nextLine();       
        return pathToFile;
    }

    public static ArrayList<String> readFromFile(String pathToFile) {
        ArrayList<String> timingOfEmployeesList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            //чтение файла построчно
            String timing;
            while ((timing = br.readLine()) != null) {
                timingOfEmployeesList.add(timing);       
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return timingOfEmployeesList;
    }

    public static int getNumOfEmploeeys(ArrayList<String> timingOfEmployeesList) {                        
        ArrayList<String> timingOfEventList = new ArrayList<>();
        for (String temp : timingOfEmployeesList) {
            String timeOfEnter = temp.substring(0, 5)+" Enter";
            String timeOfExit = temp.substring(6)+" Exit";
            timingOfEventList.add(timeOfEnter);
            timingOfEventList.add(timeOfExit);
        }
        // сортируем list по возрастанию
        Collections.sort(timingOfEventList);
        int numOfEmploeeys = 0;
        // Записываем в коллекцию количество персонала в офисе при возникновении событий Enter/Exit
        ArrayList<Integer> numOfEmploeeysList = new ArrayList<>();
        for (String time : timingOfEventList) {           
            if(time.endsWith("Enter")) {
                numOfEmploeeys++;           
            } else {
                numOfEmploeeysList.add(numOfEmploeeys);
                numOfEmploeeys--;
            }        
        }       
        return Collections.max(numOfEmploeeysList);
    }
}
