package com.example.tariq.seniordesign;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class Room extends AppCompatActivity {
    Button lightButton, tempButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room);
        lightButton = (Button) findViewById(R.id.lightButton);
        tempButton = (Button) findViewById(R.id.tempButton);

        lightButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Room.this,Light.class));

            }
        });

        tempButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Room.this,Temp.class));

            }
        });


    }
}
