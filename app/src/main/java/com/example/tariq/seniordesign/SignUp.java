package com.example.tariq.seniordesign;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends Activity
{

    // private long rowID; // id of account being edited, if any
    DatabaseConnector dd=new DatabaseConnector(this);


    private EditText fName_et,mName_et,lName_et,email_et,password_et;

    private FirebaseAuth firebaseAuth;
    // called when the Activity is first started
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState); // call super's onCreate
        setContentView(R.layout.signup); // inflate the UI

        fName_et = (EditText) findViewById(R.id.fName_et);
        mName_et = (EditText) findViewById(R.id.mName_et);
        lName_et = (EditText) findViewById(R.id.lName_et);
        email_et = (EditText) findViewById(R.id.email_et);
        password_et = (EditText) findViewById(R.id.password_et);

        Button signUp_b =
                (Button) findViewById(R.id.signUp_b);

        firebaseAuth=FirebaseAuth.getInstance();
        signUp_b.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                String email_user=email_et.getText().toString().trim();
                String pass_user=password_et.getText().toString().trim();

                firebaseAuth.createUserWithEmailAndPassword(email_user, pass_user).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()) {
                            Toast.makeText(SignUp.this, "Sign-up Successfull", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(SignUp.this,MainActivity.class));
                        }
                        else
                            Toast.makeText(SignUp.this, "Sign-up Failed", Toast.LENGTH_LONG).show();
                    }
                });


            }});





     //   signUp_b.setOnClickListener(signUpAccountButtonClicked);
    } // end method onCreate
/*
    // responds to event generated when user clicks the Sign Up Button
    OnClickListener signUpAccountButtonClicked = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {

            //check if e-mail already exists
            Boolean chk=dd.checkEmail(email_et.getText().toString());

            if (chk==false)
            {
                AsyncTask<Object, Object, Object> saveAccountTask =
                        new AsyncTask<Object, Object, Object>()
                        {
                            @Override
                            protected Object doInBackground(Object... params)
                            {
                                saveAccount();
                                return null;
                            } // end method doInBackground

                            @Override
                            protected void onPostExecute(Object result)
                            {
                                //Intent intent_list=new Intent(SignUp.this, accountsList.class);
                                //startActivity(intent_list);
                                Toast.makeText(getApplicationContext(), "You are now registered",
                                        Toast.LENGTH_LONG).show();
                                finish(); // return to the previous Activity

                            } // end method onPostExecute
                        }; // end AsyncTask

                // save the account to the database using a separate thread
                saveAccountTask.execute((Object[]) null);
            } // end if

            //do not insert values into database and notify user using toast
            else{
                Toast.makeText(getApplicationContext(), "E-mail already exists",
                        Toast.LENGTH_LONG).show();
            }

        } // end method onClick
    }; // end OnClickListener saveAccountButtonClicked

    // method that the async task calls if user inputs new email
    // saves account information to the database
    private void saveAccount()
    {
        // get DatabaseConnector to interact with the SQLite database
        DatabaseConnector databaseConnector = new DatabaseConnector(this);

        if (getIntent().getExtras() == null)
        {
            // insert the account information into the database
            databaseConnector.insertAccount(
                    fName_et.getText().toString(),
                    mName_et.getText().toString(),
                    lName_et.getText().toString(),
                    email_et.getText().toString(),
                    password_et.getText().toString()

            );
        } // end if

    }
    */
} // end class SignUp
