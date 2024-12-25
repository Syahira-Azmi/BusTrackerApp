package com.example.bustrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity {

    private ImageView currentLocationIcon, destinationLocationIcon;
    private Spinner currentSpinner, destinationSpinner;
    private Button btnConfirm;
    private ImageView busStationIcon, profileIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        Spinner currentSpinner = findViewById(R.id.current_spinner);
        String[] current_location_options = {
                "Select Current Location", "Kolej Kediaman Satria", "Kolej Kediaman Al-Jazari", "Kolej Kediaman Lestari",
                "Kampus Induk", "Kampus Teknologi", "Kompleks Sukan UTeM", "Kompleks Dewan Kuliah",
                "Pangsapuri Sri Utama(SU)", "Pangsapuri Ixora", "Emerald Park"
        };

        ArrayAdapter<String> currentAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, current_location_options
        );
        currentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currentSpinner.setAdapter(currentAdapter);

        currentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { // Avoid default value
                    Toast.makeText(Homepage.this, "Current Location: " + current_location_options[position], Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


        // Initialize views
        Spinner destinationSpinner = findViewById(R.id.destination_spinner);
        Button btnConfirm = findViewById(R.id.btnConfirm);
        ImageView busStationIcon = findViewById(R.id.bus_station_icon);
        ImageView profileIcon = findViewById(R.id.profile_icon);

        // Populate destination spinner
        String[] destination_options = {
                "Destination", "Kolej Kediaman Satria", "Kolej Kediaman Al-Jazari", "Kolej Kediaman Lestari",
                "Kampus Induk", "Kampus Teknologi", "Kompleks Sukan UTeM", "Kompleks Dewan Kuliah",
                "Pangsapuri Sri Utama(SU)", "Pangsapuri Ixora", "Emerald Park"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, destination_options
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(adapter);

        destinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int position, long id) {
                if (position > 0) { // Avoid selecting "Destination"
                    Toast.makeText(Homepage.this, "Selected: " + destination_options[position], Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Confirm button click listener
        btnConfirm.setOnClickListener(v -> {
            String currentLocation = currentSpinner.getSelectedItem() != null
                    ? currentSpinner.getSelectedItem().toString()
                    : "Unknown";
            String destination = destinationSpinner.getSelectedItem() != null
                    ? destinationSpinner.getSelectedItem().toString()
                    : "Unknown";

            Toast.makeText(Homepage.this,
                    "Current Location: " + currentLocation + "\nDestination: " + destination,
                    Toast.LENGTH_SHORT).show();
        });

        // Bus station icon listener
        busStationIcon.setOnClickListener(v -> {
            Intent intent = new Intent(Homepage.this, BusStation.class);
            startActivity(intent);
        });

// Profile icon listener
        profileIcon.setOnClickListener(v -> {
            Intent intent = new Intent(Homepage.this, StudentProfile.class);
            startActivity(intent);
        });

    }


}
