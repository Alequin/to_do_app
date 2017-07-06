package com.example.james.todolist.model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by james on 06/07/2017.
 */

public class FakeDatabase {

    public static ArrayList<Task> tasks;

    public static ArrayList<Task> queryDatabase(){
        return tasks == null ? new ArrayList<Task>() : tasks;
    }

    public static void add(Task task){
        tasks.add(task);
    }

    public static void remove_by_index(int index){
        tasks.remove(index);
    }

    public static int size(){
        return tasks.size();
    }

    public static void buildDatabase(){
        Calendar date = Calendar.getInstance();

        Task task1 = new Task("buy food", "go to the shop", date, date, false);
        task1.setCreationDate(1, 1, 2017);
        task1.setDueDate(2, 1, 2017);
        task1.save();

        Task task2 = new Task("walk dog", "take the dog out", date, date, false);
        task2.setCreationDate(15, 2, 2017);
        task2.setDueDate(30, 2, 2017);
        task2.save();

        Task task3 = new Task("complete work", "", date, date, false);
        task3.setCreationDate(25, 2, 2017);
        task3.setDueDate(10, 3, 2017);
        task3.save();

        Task task4 = new Task("cook food", "find an interesting recipe", date, date, false);
        task4.setCreationDate(4, 3, 2017);
        task4.setDueDate(20, 3, 2017);
        task4.save();

        Task task5 = new Task("iron clothes", "", date, date, false);
        task5.setCreationDate(13, 4, 2017);
        task5.setDueDate(9, 6, 2017);
        task5.save();

        Task task6 = new Task("go for a run", "", date, date, false);
        task6.setCreationDate(17, 5, 2017);
        task6.setDueDate(20, 6, 2017);
        task6.save();
    }

}
