package com.mycompany.todo;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import javax.swing.*;
import java.awt.*;

public class Todo {
    //Gui
    public static DefaultListModel<String> listModel = new DefaultListModel<>();
    public static JList<String> taskList = new JList<>(listModel);
    public static JTextField inputField = new JTextField(20);
    public static void createGUI() {
//------------------------------------------------------------------------------
    JFrame frame = new JFrame("Todo App");
    frame.setSize(400, 500);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());  // Make The app have sections
//------------------------------------------------------------------------------
    // Top (input + add button)
    JPanel topPanel = new JPanel();
    JButton addButton = new JButton("Add Task");
    //Intput's (Button & Field).
    topPanel.add(inputField);
    topPanel.add(addButton);
//------------------------------------------------------------------------------
    // Center (task list)
    refreshList();
    JScrollPane scrollPane = new JScrollPane(taskList);
//------------------------------------------------------------------------------
    // Bottom (delete button)
    JButton deleteButton = new JButton("Delete Selected");
//------------------------------------------------------------------------------
    // Set the location of Every Section.
    frame.add(topPanel, BorderLayout.NORTH);
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.add(deleteButton, BorderLayout.SOUTH);
//------------------------------------------------------------------------------
    // 🔥 Actions
    addButton.addActionListener(e -> {
        String text = inputField.getText().trim();

        if (!text.isEmpty()) {
            arr.add(text);
            saveTasks();
            refreshList();
            inputField.setText("");
        }
    });
    deleteButton.addActionListener(e -> {
        int index = taskList.getSelectedIndex();

        if (index != -1) {
            int confirm = JOptionPane.showConfirmDialog(
                    frame, 
                    "Are You Sure you want to delete this Task?",
                    "Confirm Delete",
                    JOptionPane.YES_NO_OPTION
                    );
            if (confirm == JOptionPane.YES_OPTION){
                arr.remove(index);
                saveTasks();
                refreshList();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Select a task first.");
        }
    });
    frame.setVisible(true);
}
public static void refreshList() {
    listModel.clear();
    for (String task : arr) {
        listModel.addElement(task);
    }
}

//End of GUI--------------------------------------------------------------------
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
        createGUI();
        int choice = 0 ;
        System.out.println("Welcome At the Todo List App: ");   
        do{
            System.out.println("\nChoose an option:");
            System.out.println("1- Add Task");
            System.out.println("2- View Tasks");
            System.out.println("3- Delete Task");
            System.out.println("0- Exit");
            System.out.println("------------------------------------\n");
            if(input.hasNextInt()){
                choice = input.nextInt();  
            }
            else{
                System.out.println("Invalid input. Please Enter Numbers Only...");
                input.nextLine();
                continue;
            }
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
