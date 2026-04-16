package com.mycompany.todo;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class Todo {
    //Buffer writer to write the file in the System and save it. 
    public static void saveTasks(){
        try{
        BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));
           for (String task : arr){
               writer.write(task);
               writer.newLine();
           }
           writer.close();
        }catch(IOException e){
            System.out.println("Error Saving Tasks.");
        }
    }
    public static void loadTasks(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("tasks.txt"));
            String line;
            
            while ((line = reader.readLine())!= null){
                arr.add(line);
            }
            reader.close();
        }
        catch(IOException e){
            System.out.println("No Previous tasks found.");
        }
    }
    public static ArrayList<String> arr = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);
    public static void task(){
        System.out.println("\nEnter the Task: ");
        arr.add(input.nextLine());
        saveTasks();
        System.out.println("\nTask Added Successfully.\n");
    }
    public static void view(){
        if(arr.isEmpty()){
            System.out.println("No tasks available.");
        } else {
            for(int i = 0; i < arr.size(); i++){
                System.out.println((i+1) + " - " + arr.get(i));
            }
            System.out.println("______________________________________________");
        }
    }
    public static void del(int del){
        if(del>=0&&del<arr.size()){
            arr.remove((del-1));
            saveTasks();
            System.out.println("\nTask Deleted Successfully.\n");
        }
        else{
            System.out.println("\nInvalid Index.\n ");}
    }
    public static void main(String[] args) {
        loadTasks();
        int choice ;
        System.out.println("Welcome At the Todo List App: ");   
        do{
            System.out.println("\nChoose an option:");
            System.out.println("1- Add Task");
            System.out.println("2- View Tasks");
            System.out.println("3- Delete Task");
            System.out.println("0- Exit");
            System.out.println("------------------------------------\n");
            choice =input.nextInt();
            input.nextLine();
            switch(choice) {
                case 1:
                    task();
                    break;
                case 2:
                    System.out.println();
                    view();
                    break;
                case 3:
                    int ch ;
                    System.out.println("\nEnter the number Item You need to delete: ");
                    view();
                    ch = input.nextInt(); 
                    input.nextLine();
                    del(ch);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid Input. ");
            }
        }while(choice!=0);   
        System.out.println("Goodbye!");
    }
}
