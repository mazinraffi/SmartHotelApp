package com.example.tariq.seniordesign;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends Activity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user temp");



    public static final String ROW_ID = "row_id";
    //String ll="e" ;
    Button signUpMain_b, loginMain_b;
    DatabaseConnector mdc = new DatabaseConnector(this);
    EditText emailMain_et, passwordMain_et;

    //private SharedPreferences prefs;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        myRef.setValue("hello");

        emailMain_et = (EditText) findViewById(R.id.emailMain_et);
        passwordMain_et = (EditText) findViewById(R.id.passwordMain_et);
        loginMain_b = (Button) findViewById(R.id.loginMain_b);

       /*

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {
            finish();
            startActivity(new Intent(MainActivity.this, AccountView.class));
        }
*/


       /*  //This is for the local storage method
        prefs=PreferenceManager.getDefaultSharedPreferences(this);
        final String logged_email=emailMain_et.getText().toString();
        SharedPreferences.Editor editor=prefs.edit();
        editor.putString("logged_in_email",logged_email);
        editor.commit();
        loginMain_b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Boolean chklogin=mdc.matchEmailPassword(emailMain_et.getText().toString(),passwordMain_et.getText().toString());
                if(chklogin==true){

                    // Toast.makeText(getApplicationContext(), "login successful",
                    //       Toast.LENGTH_LONG).show();

                    Intent testAccount=new Intent(MainActivity.this,AccountView.class);
                    String ll=prefs.getString("logged_in_email", null);
                    testAccount.putExtra(ROW_ID,emailMain_et.getText().toString());
                    startActivity(testAccount);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Email and Password do not match",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
        */

        firebaseAuth=FirebaseAuth.getInstance();
        loginMain_b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String email_user=emailMain_et.getText().toString().trim();
                String pass_user=passwordMain_et.getText().toString().trim();

                Log.e("email",email_user);
                Log.e("pass",pass_user);
                firebaseAuth.signInWithEmailAndPassword(email_user, pass_user).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login Successfull", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this,Room.class));
                        }
                        else
                            Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                    }
                });


            }});

        signUpMain_b = (Button) findViewById(R.id.signUpMain_b);
        signUpMain_b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp.class);
                startActivity(intent);
            }
        });



    }



}
