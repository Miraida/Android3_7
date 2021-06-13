package com.geek.android3_7.framework.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.geek.android3_7.framework.room.model.Polyline;

@Dao
public interface PolylineDao {
    @Query("select * from polyline")
    Polyline getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Polyline polyline);
}
