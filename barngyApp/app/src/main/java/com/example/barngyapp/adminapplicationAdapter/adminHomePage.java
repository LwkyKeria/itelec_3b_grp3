package com.example.barngyapp.adminapplicationAdapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barngyapp.R;

public class adminHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_home_page);

        // Set gradient text for TextView
        setupGradientText();

        // Handle window insets for padding
        setupWindowInsets();

        // Set up click listeners for buttons
        setupClickListeners();
    }

    private void setupGradientText() {
        TextView textView = findViewById(R.id.textView21); // Ensure this ID exists in XML
        if (textView == null) {
            Log.e("adminHomePage", "TextView with ID textView21 is null. Check XML.");
            return;
        }

        TextPaint paint = textView.getPaint();
        float width = paint.measureText(textView.getText().toString());

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#FF5722"), // Start color
                        Color.parseColor("#FFC107")  // End color
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);
    }

    private void setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void setupClickListeners() {
        // Click listener for Services
        RelativeLayout RLservice = findViewById(R.id.RLservice);
        if (RLservice == null) {
            Log.e("adminHomePage", "RLservice is null. Check the ID in admin_home_page.xml.");
        } else {
            RLservice.setOnClickListener(v -> {
                Toast.makeText(adminHomePage.this, "Navigating to Services...", Toast.LENGTH_SHORT).show();
                try {
                    Intent intent = new Intent(adminHomePage.this, adminservices.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("adminHomePage", "Error navigating to adminservices", e);
                    Toast.makeText(adminHomePage.this, "Error navigating to Services.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Click listener for Events
        RelativeLayout RLevent = findViewById(R.id.Rlevent);
        if (RLevent == null) {
            Log.e("adminHomePage", "RLevent is null. Check the ID in admin_home_page.xml.");
        } else {
            RLevent.setOnClickListener(v -> {
                Toast.makeText(adminHomePage.this, "Navigating to Events...", Toast.LENGTH_SHORT).show();
                try {
                    Intent intent = new Intent(adminHomePage.this, admin_eventmaker.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("adminHomePage", "Error navigating to admin_eventmaker", e);
                    Toast.makeText(adminHomePage.this, "Error navigating to Events.", Toast.LENGTH_SHORT).show();
                }
            });
        }

        // Click listener for Info
        RelativeLayout RLinfo = findViewById(R.id.RLinfo);
        if (RLinfo == null) {
            Log.e("adminHomePage", "RLinfo is null. Check the ID in admin_home_page.xml.");
        } else {
            RLinfo.setOnClickListener(v -> {
                Toast.makeText(adminHomePage.this, "Navigating to Info...", Toast.LENGTH_SHORT).show();
                try {
                    Intent intent = new Intent(adminHomePage.this, AdminInfoActivity.class);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e("adminHomePage", "Error navigating to AdminInfoActivity", e);
                    Toast.makeText(adminHomePage.this, "Error navigating to Info.", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
