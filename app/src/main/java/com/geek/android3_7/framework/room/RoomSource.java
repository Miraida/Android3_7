package com.geek.android3_7.framework.room;

import com.geek.android3_7.App;
import com.geek.android3_7.framework.room.model.Polyline;
import com.geek.android3_7.domain.source.DrawSource;

public class RoomSource implements DrawSource {
    @Override
    public Polyline getPolyline() {
        Polyline polyline = App.database.polylineDao().getAll();
        if (polyline == null)
            return new Polyline();
        return polyline;
    }

    @Override
    public void insert(String points, Integer id) {
        App.database.polylineDao().insert(new Polyline(id, points));
    }
}
