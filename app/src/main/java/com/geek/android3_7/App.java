package com.geek.android3_7;

import android.app.Application;

import androidx.room.Room;

import com.geek.android3_7.domain.repository.DrawRepository;
import com.geek.android3_7.framework.room.Database;
import com.geek.android3_7.framework.room.RoomSource;

public class App extends Application {
    public static Database database;
    public static DrawRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        createDatabase();
        setSource();
    }

    private void createDatabase() {
        database = Room.databaseBuilder(this, Database.class, "database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

    private void setSource() {
        RoomSource roomSource = new RoomSource();
        repository = new DrawRepository(roomSource);
    }
}
