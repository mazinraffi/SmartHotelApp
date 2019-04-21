package com.example.tariq.seniordesign;

import android.renderscript.Sampler;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Post {
    private String temp;

    public Post(String temp) {
        temp = temp;
    }

    //final FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=FirebaseDatabase.getInstance().getReferenceFromUrl("https://senior-design-2-230507.firebaseio.com/");



    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        ref.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot){
                Post post=dataSnapshot.getValue(Post.class);

            }

            public void onCancelled(DatabaseError dataError){
                System.out.println("The read Failed: "+ dataError.getCode());
            }
        });
    }

}
