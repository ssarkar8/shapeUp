package com.example.shapeup;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        TextView textBox = findViewById(R.id.thing);
        textBox.setText("");
        Button messageButton = findViewById(R.id.message);
        messageButton.setOnClickListener(unused -> {
            textBox.setText("The button was pressed!");
        });
    }



}
