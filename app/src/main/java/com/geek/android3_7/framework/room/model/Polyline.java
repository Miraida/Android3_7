package com.geek.android3_7.framework.room.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Polyline {
    @PrimaryKey
    private Integer id;
    private String points;

    public Polyline(Integer id, String points) {
        this.id = id;
        this.points = points;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Polyline() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
