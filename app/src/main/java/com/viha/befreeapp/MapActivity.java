package com.viha.befreeapp;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String start, end;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng startLocation = new LatLng(-34, 151); // Replace with actual coordinates from start
        LatLng endLocation = new LatLng(-35, 152); // Replace with actual coordinates from end

        mMap.addMarker(new MarkerOptions().position(startLocation).title("Start Location"));
        mMap.addMarker(new MarkerOptions().position(endLocation).title("End Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(startLocation, 10));

        // You will need to fetch the actual coordinates from the Google Directions API and draw the route here
    }
}
