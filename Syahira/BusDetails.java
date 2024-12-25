package com.example.bustrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BusDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);

        // Initialize navigation buttons
        LinearLayout routeInfoNav = findViewById(R.id.routeInfoNav);
        LinearLayout manageUserNav = findViewById(R.id.manageUserNav);
        LinearLayout profileNav = findViewById(R.id.profileNav);

        // Set click listener for Route Info navigation
        routeInfoNav.setOnClickListener(v -> {
            Toast.makeText(BusDetails.this, "Route Info clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BusDetails.this, RouteInfo.class));
        });

        // Set click listener for Manage User navigation
        manageUserNav.setOnClickListener(v -> {
            Toast.makeText(BusDetails.this, "Manage User clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BusDetails.this, ManageUser.class));
        });

        // Set click listener for Profile navigation
        profileNav.setOnClickListener(v -> {
            Toast.makeText(BusDetails.this, "Profile clicked", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(BusDetails.this, AdminProfile.class));
        });
    }
}
