package com.example.myapplication;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.StyleSpan;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PermissionActivity extends AppCompatActivity {

    private TextView permissionsTextView;
    private TextView titleTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        // Set up the Go Back button
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(PermissionActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        permissionsTextView = findViewById(R.id.permissionsTextView);
        titleTextView = findViewById(R.id.titleTextView);


        String packageName = getIntent().getStringExtra("packageName");
        String appName = getIntent().getStringExtra("appName");

        // Set the title text to "Permissions of <appName>"
        if (appName != null) {
            titleTextView.setText("Permissions of " + appName + ":");
        }

        if (packageName != null) {
            displayPermissions(packageName);
        }
    }

    private void displayPermissions(String packageName) {
        try {
            PackageManager pm = getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);
            String[] requestedPermissions = packageInfo.requestedPermissions;

            SpannableStringBuilder categorizedPermissions = new SpannableStringBuilder();

            // Append each category
            appendCategory(categorizedPermissions, "Camera Permissions", requestedPermissions, "CAMERA");
            appendCategory(categorizedPermissions, "Location Permissions", requestedPermissions, "ACCESS_FINE_LOCATION", "ACCESS_COARSE_LOCATION");
            appendCategory(categorizedPermissions, "Microphone Permissions", requestedPermissions, "RECORD_AUDIO");
            appendCategory(categorizedPermissions, "Contacts Permissions", requestedPermissions, "READ_CONTACTS", "WRITE_CONTACTS", "GET_ACCOUNTS");
            appendCategory(categorizedPermissions, "Storage Permissions", requestedPermissions, "READ_EXTERNAL_STORAGE", "WRITE_EXTERNAL_STORAGE", "MANAGE_EXTERNAL_STORAGE");
            appendCategory(categorizedPermissions, "SMS Permissions", requestedPermissions, "SEND_SMS", "RECEIVE_SMS", "READ_SMS", "RECEIVE_WAP_PUSH", "RECEIVE_MMS");
            appendCategory(categorizedPermissions, "Phone Permissions", requestedPermissions, "READ_PHONE_STATE", "CALL_PHONE", "READ_CALL_LOG", "WRITE_CALL_LOG", "ADD_VOICEMAIL", "USE_SIP", "PROCESS_OUTGOING_CALLS", "ANSWER_PHONE_CALLS");
            appendCategory(categorizedPermissions, "Calendar Permissions", requestedPermissions, "READ_CALENDAR", "WRITE_CALENDAR");
            appendCategory(categorizedPermissions, "Sensors Permissions", requestedPermissions, "BODY_SENSORS");
            appendCategory(categorizedPermissions, "Network Permissions", requestedPermissions, "ACCESS_NETWORK_STATE", "INTERNET");
            appendCategory(categorizedPermissions, "Others", requestedPermissions);

            permissionsTextView.setText(categorizedPermissions);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            permissionsTextView.setText("Error retrieving permissions");
        }
    }

    private void appendCategory(SpannableStringBuilder builder, String categoryName, String[] permissions, String... categories) {
        // Create a SpannableString for the category name with bold and larger font
        SpannableString categorySpannable = new SpannableString(categoryName + ":\n");
        categorySpannable.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, categorySpannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        categorySpannable.setSpan(new AbsoluteSizeSpan(20, true), 0, categorySpannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(categorySpannable);

        boolean found = false;
        if (permissions != null) {
            for (String permission : permissions) {
                for (String category : categories) {
                    if (permission.contains(category)) {
                        // Create a SpannableString for the permission with smaller font
                        SpannableString permissionSpannable = new SpannableString("  - " + permission + "\n");
                        permissionSpannable.setSpan(new AbsoluteSizeSpan(12, true), 0, permissionSpannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        builder.append(permissionSpannable);
                        found = true;
                    }
                }
            }
        }
        if (!found) {
            SpannableString noneSpannable = new SpannableString("  None\n");
            noneSpannable.setSpan(new AbsoluteSizeSpan(12, true), 0, noneSpannable.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append(noneSpannable);
        }
        builder.append("\n");
    }
}