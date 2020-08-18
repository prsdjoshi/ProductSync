package com.assessment.productinfo.model.roomdatabase;


import androidx.databinding.adapters.Converters;
import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {ProductTable.class}, version = 1,exportSchema = false)

public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}

