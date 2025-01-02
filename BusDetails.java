package com.example.bustrackerapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BusDetails extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText busIdEditText;
    private TextView capacityTextView, statusTextView, userIdTextView, scheduleIdTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_details);

        // Initialize views
        busIdEditText = findViewById(R.id.busIdEditText);  // Assuming you have an EditText for Bus ID input
        capacityTextView = findViewById(R.id.capacityTextView);
        statusTextView = findViewById(R.id.statusTextView);
        userIdTextView = findViewById(R.id.userIdTextView);
        scheduleIdTextView = findViewById(R.id.scheduleIdTextView);
        Button searchButton = findViewById(R.id.searchUserButton);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Set up click listener for search button
        searchButton.setOnClickListener(v -> searchBusDetails());
    }

    private void searchBusDetails() {
        String busIdInput = busIdEditText.getText().toString().trim();

        if (busIdInput.isEmpty()) {
            Toast.makeText(BusDetails.this, "Please enter a Bus ID", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int busId = Integer.parseInt(busIdInput);  // Parse the Bus ID to an integer

            // Query the database for the bus details
            Cursor cursor = dbHelper.getBusDetailsByID(busId);

            if (cursor != null && cursor.moveToFirst()) {
                // Retrieve and display the bus details
                int capacity = cursor.getInt(cursor.getColumnIndex("capacity"));
                String status = cursor.getString(cursor.getColumnIndex("status"));
                int userID = cursor.getInt(cursor.getColumnIndex("userID"));
                int scheduleID = cursor.getInt(cursor.getColumnIndex("scheduleID"));

                // Update the TextViews with the fetched data
                capacityTextView.setText("Capacity: " + capacity);
                statusTextView.setText("Status: " + status);
                userIdTextView.setText("User ID: " + userID);
                scheduleIdTextView.setText("Schedule ID: " + scheduleID);

                cursor.close();  // Close the cursor
            } else {
                Toast.makeText(BusDetails.this, "No bus found with the given Bus ID", Toast.LENGTH_SHORT).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(BusDetails.this, "Invalid Bus ID format", Toast.LENGTH_SHORT).show();
        }
    }
}
