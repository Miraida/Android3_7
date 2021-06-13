package com.geek.android3_7.domain.repository;

import com.geek.android3_7.domain.source.DrawSource;
import com.geek.android3_7.framework.room.model.Polyline;

public class DrawRepository {
    private final DrawSource source;

    public DrawRepository(DrawSource source) {
        this.source = source;
    }

    public Polyline getPolyline() {
        return source.getPolyline();
    }

    public void insert(String points, Integer id) {
        source.insert(points, id);
    }
}
