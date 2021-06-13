package com.geek.android3_7.domain.source;

import com.geek.android3_7.framework.room.model.Polyline;

public interface DrawSource {
    Polyline getPolyline();
    void insert(String points,Integer id);
}
