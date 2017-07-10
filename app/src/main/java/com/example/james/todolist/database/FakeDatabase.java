package com.example.james.todolist.database;

import com.example.james.todolist.model.Task;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by james on 06/07/2017.
 */

public class FakeDatabase {

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static ArrayList<Task> queryDatabase(){
        return tasks == null ? new ArrayList<Task>() : tasks;
    }

    public static void add(Task task){
        tasks.add(task);
    }

    public static void update(int index, Task task){
        tasks.add(index, task);
    }

    public static void remove_by_index(int index){
        tasks.remove(index);
    }

    public static int size(){
        return tasks.size();
    }

    public static void buildDatabase(){
        Calendar date = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();

        Task task4 = new Task("cook food", "find an interesting recipe", date, date2, false);
        task4.setCreationDate(2017, 3, 4);
        task4.setDueDate(2017, 3, 20);
        task4.save();

        Task task5 = new Task("iron clothes", "", date, date2, false);
        task5.setCreationDate(2017, 4, 13);
        task5.setDueDate(2017, 6, 9);
        task5.save();

        Task task3 = new Task("complete work", "", date, date2, false);
        task3.setCreationDate(2017, 2, 25);
        task3.setDueDate(2017, 3, 10);
        task3.save();

        Task task1 = new Task("buy food", "go to the shop", date, date2, true);
        task1.setCreationDate(2017, 1, 1);
        task1.setDueDate(2017, 1, 2);
        task1.save();

        Task task2 = new Task("walk dog", "take the dog out", date, date2, false);
        task2.setCreationDate(2017, 2, 15);
        task2.setDueDate(2017, 2, 30);
        task2.save();

        Task task6 = new Task("go for a run", "", date, date2, false);
        task6.setCreationDate(2017, 5, 17);
        task6.setDueDate(2017, 6, 20);
        task6.save();
    }

}
