package com.touchsoft;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import com.touchsoft.numberofemployees.NumOfEmployees;

public class NumOfEmployeesTest {
    
    private static String expectedPathToFile;
    
    public NumOfEmployeesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {        
        File file = new File("Test.txt");     
        try(FileWriter writer = new FileWriter(file, false)) {
            expectedPathToFile = file.getAbsolutePath();
            ArrayList<String> timingList = getListOfTimings();
            for (String timing : timingList) {
                writer.write(timing+"\r\n");
            }
            writer.flush();         
        }
        catch(IOException ex){             
            System.out.println(ex.getMessage());
        } 
    }
    
    private static ArrayList<String> getListOfTimings(){
        String timingOfEmp1 = "00:00 06:00";
        String timingOfEmp2 = "05:00 14:20";
        String timingOfEmp3 = "08:05 17:05";
        String timingOfEmp4 = "11:20 18:40";
        String timingOfEmp5 = "11:30 14:20";
        String timingOfEmp6 = "16:20 18:30";
        String timingOfEmp7 = "18:00 23:59";
        ArrayList<String> timingList = new ArrayList<>();
        timingList.add(timingOfEmp1);
        timingList.add(timingOfEmp2);
        timingList.add(timingOfEmp3);
        timingList.add(timingOfEmp4);
        timingList.add(timingOfEmp5);
        timingList.add(timingOfEmp6);
        timingList.add(timingOfEmp7);
        return timingList;
    }                       
    
    @AfterClass
    public static void tearDownClass() {
        System.setIn(System.in);
        File file = new File(expectedPathToFile);
        file.delete();
    }
   
    @Test
    public void getPathToFileTest() {
        ByteArrayInputStream in = new ByteArrayInputStream("Test.txt".getBytes());
        System.setIn(in);
        String actualPathToFile = NumOfEmployees.getPathToFile();
        System.out.println(actualPathToFile);
        assertEquals("Test.txt", actualPathToFile);              
    }
    
    @Test
    public void readFromFileTest() {
        ArrayList<String> expectedList = getListOfTimings(); 
        ArrayList<String> actualList = NumOfEmployees.readFromFile(expectedPathToFile);        
        assertEquals(expectedList, actualList);              
    }
    
    @Test
    public void getNumOfEmploeeysTest() {
        ArrayList<String> timingList = getListOfTimings();
        int actualNumOfEmployees = NumOfEmployees.getNumOfEmploeeys(timingList);
        int expectedNumOfEmployees = 4;              
        assertEquals(expectedNumOfEmployees, actualNumOfEmployees);              
    }   
}