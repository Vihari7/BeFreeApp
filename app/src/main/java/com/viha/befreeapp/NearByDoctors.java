package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class NearByDoctors extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private EditText searchLocation;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_by_doctors);

        searchLocation = findViewById(R.id.search_location);
        searchButton = findViewById(R.id.search_button);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = searchLocation.getText().toString();
                if (!location.isEmpty()) {
                    searchForLocation(location);
                } else {
                    Toast.makeText(NearByDoctors.this, "Please enter a location", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng doctorLocation = new LatLng(6.821830492277035, 79.88054819085279);
        mMap.addMarker(new MarkerOptions().position(doctorLocation).title("Dr. M. B. S. Niranjala Fernando"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(doctorLocation, 12));
    }

    private void searchForLocation(String location) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addressList;

        try {
            addressList = geocoder.getFromLocationName(location, 1);
            if (addressList != null && !addressList.isEmpty()) {
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.addMarker(new MarkerOptions().position(latLng).title(address.getAddressLine(0)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            } else {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Unable to find location", Toast.LENGTH_SHORT).show();
        }
    }
}
