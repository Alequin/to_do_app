package com.example.james.todolist.database;

/**
 * Created by james on 07/07/2017.
 */

public class DatabaseHandler {

    private static DatabaseHandler dbHandler;

    public static void initialiseDatabase(){
        dbHandler = new DatabaseHandler();
    }

    public static DatabaseHandler getDatabase(){
        if(dbHandler == null){
            initialiseDatabase();
        }
        return dbHandler;
    }
}
