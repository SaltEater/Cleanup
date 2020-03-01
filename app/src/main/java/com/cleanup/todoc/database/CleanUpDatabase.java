package com.cleanup.todoc.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.cleanup.todoc.database.dao.TaskDao;
import com.cleanup.todoc.model.Task;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class CleanUpDatabase extends RoomDatabase {

    // Singleton
    private static CleanUpDatabase INSTANCE;

    // Dao
    public abstract TaskDao taskDao();

    // Instance
    public static synchronized CleanUpDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    CleanUpDatabase.class, "MyDatabase.db")
                    .fallbackToDestructiveMigration()
                    .build();
            }
        return INSTANCE;
    }
}
