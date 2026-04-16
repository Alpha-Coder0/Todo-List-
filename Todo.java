package com.mycompany.todo;
import java.util.Scanner;
import java.util.ArrayList;
public class Todo {
    public static ArrayList<String> arr = new ArrayList<>();
    public static Scanner input = new Scanner(System.in);
    public static void task(){
        System.out.println("Enter the Task: ");
        arr.add(input.nextLine());
        System.out.println("Task Added Successfully.\n");
    }
    public static void view(){
        if(arr.isEmpty()){
            System.out.println("No tasks available.");
        } else {
            for(int i = 0; i < arr.size(); i++){
                System.out.println(i + " - " + arr.get(i));
            }
        }
    }
    public static void del(int del){
        if(del>=0&&del<arr.size()){
            arr.remove(del);
            System.out.println("Task Deleted Successfully.\n");
        }
        else{
            System.out.println("Invalid Index. ");}
    }
    public static void main(String[] args) {
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
                default:
                    System.out.println("Invalid Input. ");
            }
        }while(choice!=0);   
        System.out.println("Goodbye!");
    }
}
