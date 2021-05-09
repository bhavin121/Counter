package com.example.counterexp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.counterwidget.Counter;
import com.example.counterwidget.OnChangeListener;
import com.example.counterwidget.OnZeroListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Counter counter = findViewById(R.id.counter);

        counter.setOnChangeListener(new OnChangeListener() {
            @Override
            public void onChange(int value) {
                System.out.println(value);
            }
        });

        counter.setOnZeroListener(new OnZeroListener() {
            @Override
            public void onZero() {
                System.out.println("Zero Value Reached");
            }
        });
    }
}