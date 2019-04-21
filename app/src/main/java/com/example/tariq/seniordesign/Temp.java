package com.example.tariq.seniordesign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

public class Temp extends AppCompatActivity {

    DatabaseReference databaseReference;
    private TextView tempView;
    String temp="test";
    private Switch switch1;
    Float y,temp_f;
    String conv=null;
    String x=null;
    private TextView setTemp;
    private Button setIt;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user temp");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tempview);

        tempView=(TextView) findViewById(R.id.tempView);
        switch1=(Switch) findViewById(R.id.switch1);
        setTemp=(TextView) findViewById(R.id.setTemp);
        setIt=(Button) findViewById(R.id.setIt);


        databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://senior-design-2-230507.firebaseio.com/");

        databaseReference.limitToFirst(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    temp = child.getKey();
                    x=child.child("temp").getValue().toString();
                    System.out.println("here si the data==>>" + child.getKey());
                    Log.e(TAG, "onDataChange: "+temp);
                    Log.e(TAG, "temp: "+x);
                    //tempView.setText(x+" \u2109");
                    tempView.setText(x+" \u2103");

                    switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            // do something, the isChecked will be
                            // true if the switch is in the On position
                            if(buttonView.isChecked()){
                                y = Float.parseFloat(x);
                                temp_f= ((float)9/5)*y + 32;
                                conv=temp_f.toString();
                                tempView.setText(conv+" \u2109");
                            }
                            else
                                tempView.setText(x+" \u2103");

                        }
                    });





                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        setIt.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String temperatureFromUser=setTemp.getText().toString().trim();
                myRef.setValue(temperatureFromUser);

            }});

        /*
        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                temp= (String)dataSnapshot.child("Room_info").child("temp").getValue();
                //temp=dataSnapshot.child("-L_Wu02i5HA5SA0BYME0").getValue().toString();
                //temp=dataSnapshot.getChildren().iterator().next().toString();
                //System.out.println("temp: " +temp);
                Toast.makeText(Temp.this,temp,Toast.LENGTH_LONG).show();

                tempView.setText(temp);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    */
    }


}
