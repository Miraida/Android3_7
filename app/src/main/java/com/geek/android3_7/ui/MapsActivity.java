package com.geek.android3_7.ui;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.geek.android3_7.App;
import com.geek.android3_7.R;
import com.geek.android3_7.databinding.ActivityMapsBinding;
import com.geek.android3_7.utils.ListConverter;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding ui;
    private ActivityResultLauncher<String[]> resultLauncher;
    private List<LatLng> points = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());
        checkPermissions();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        setOnClicks();

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(this);
        checkDataFromRepo();
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {
        MarkerOptions markerOptions = new MarkerOptions()
                .draggable(true)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
                .position(latLng);
        mMap.addMarker(markerOptions);
        points.add(latLng);
    }

    private void checkDataFromRepo() {
        if (App.repository.getPolyline().getPoints() != null) {
            this.points = ListConverter.toLatlangList(App.repository.getPolyline().getPoints());
            drawPolyline(points);
        }
    }

    private void drawPolyline(List<LatLng> points) {
        PolylineOptions polylineOptions = new PolylineOptions()
                .color(getResources().getColor(R.color.polyline_color))
                .width(15f)
                .addAll(points);
        mMap.addPolyline(polylineOptions);
    }

    private void checkPermissions() {
        resultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
            for (Map.Entry<String, Boolean> entry : result.entrySet()) {
                if (!entry.getValue()) launchRequest();
            }
        });
        launchRequest();
    }

    private void launchRequest() {
        resultLauncher.launch(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION});
    }


    private void setOnClicks() {
        ui.drawPolyline.setOnClickListener(v -> drawPolyline(points));
        ui.deletePolyline.setOnClickListener(v -> {
            mMap.clear();
            points.removeAll(Collections.unmodifiableList(points));
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (points != null) {
            App.repository.insert(ListConverter.fromLatlangList(points), 1);
            Log.d("TAG", "onPause: " + ListConverter.fromLatlangList(points));
        }
    }
}