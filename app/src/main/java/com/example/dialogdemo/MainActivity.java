package com.example.dialogdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.dialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkShowDialog dialog = new WorkShowDialog(MainActivity.this, new WorkShowDialog.DialogCallback() {
                    @Override
                    public void callback(String message) {
                        TextView text = findViewById(R.id.main);
                        text.setText(message);
                    }
                });
                dialog.show();
            }
        });
    }
}