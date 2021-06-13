package com.geek.android3_7.framework.room;

import androidx.room.RoomDatabase;

import com.geek.android3_7.framework.room.model.Polyline;

@androidx.room.Database(entities = {Polyline.class},version = 2)
public abstract class Database extends RoomDatabase {
    public abstract PolylineDao polylineDao();
}
