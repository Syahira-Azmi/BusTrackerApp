package com.example.bustrackerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        // Back Button Logic
        findViewById(R.id.back_arrow).setOnClickListener(view -> finish());
        // Accessing the EditText for "Current Location"
        EditText currentLocationEdit = findViewById(R.id.current_location_edit);

        // Destination Spinner Logic
        Spinner destinationSpinner = findViewById(R.id.destination_spinner);
        String[] destination_options = {"Destination", "Kolej Kediaman Satria", "Kolej Kediaman Al-Jazari", "Kolej Kediaman Lestari",
        "Kampus Induk", "Kampus Teknologi","Kompleks Sukan UTeM", "Kompleks Dewan Kuliah", "Pangsapuri Sri Utama(SU)", "Pangsapuri Ixora",
        "Emerald Park" };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, destination_options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(adapter);

        destinationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { // Avoid selecting "Destination"
                    Toast.makeText(Homepage.this, "Selected: " + destination_options[position], Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Confirm Button Logic
        Button confirmButton = findViewById(R.id.btnConfirm);
        confirmButton.setOnClickListener(v -> {
            String currentLocation = currentLocationEdit.getText().toString().trim();
            String selectedItem = destinationSpinner.getSelectedItem().toString();
            if (currentLocation.isEmpty()) {
                Toast.makeText(Homepage.this, "Please enter your current location!", Toast.LENGTH_SHORT).show();
                if (!selectedItem.equals("Destination")) {
                    Toast.makeText(Homepage.this, "Confirmed: " + selectedItem, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Homepage.this, "Please select a destination!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

