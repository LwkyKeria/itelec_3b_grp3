package com.example.barngyapp.adminapplicationAdapter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barngyapp.R;
import com.example.barngyapp.adminapplicationAdapter.adminservices; // Ensure this import is correct

public class adminHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.admin_home_page);

        TextView textView = findViewById(R.id.textView21);
        TextPaint paint = textView.getPaint();
        float width = paint.measureText(textView.getText().toString());

        Shader textShader = new LinearGradient(0, 0, width, textView.getTextSize(),
                new int[]{
                        Color.parseColor("#FF5722"), // Start color
                        Color.parseColor("#FFC107")  // End color
                }, null, Shader.TileMode.CLAMP);
        textView.getPaint().setShader(textShader);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupClickListeners();
    }

    private void setupClickListeners() {
        RelativeLayout RLservice = findViewById(R.id.RLservice); // Ensure this ID is correct in XML
        RLservice.setOnClickListener(v -> {
            Toast.makeText(adminHomePage.this, "Navigating to Services...", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(adminHomePage.this, adminservices.class); // Confirm adminservices.class is correct
            startActivity(intent);
        });
    }
}
