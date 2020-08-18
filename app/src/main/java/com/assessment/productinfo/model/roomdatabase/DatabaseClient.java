package com.assessment.productinfo.model.roomdatabase;
import android.content.Context;
import android.os.Environment;


import androidx.room.Room;

import java.io.File;


public class DatabaseClient {
    private Context context;
    private static AppDatabase appDatabase;
    private static DatabaseClient dbClient;

    private DatabaseClient(Context context){

        appDatabase = Room.databaseBuilder(context, AppDatabase.class,  "productinfo").allowMainThreadQueries().build();
    }

    public static synchronized DatabaseClient getInstance(Context context){
        if (dbClient == null){
            dbClient = new DatabaseClient(context);
        }
        return dbClient;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}

