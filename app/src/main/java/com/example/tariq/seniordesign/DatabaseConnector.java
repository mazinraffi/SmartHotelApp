package com.example.tariq.seniordesign;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DatabaseConnector
{
    // database name
    private static final String DATABASE_NAME = "UserAccounts";
    private SQLiteDatabase database; // database object
    private DatabaseOpenHelper databaseOpenHelper; // database helper




    // public constructor for DatabaseConnector
    public DatabaseConnector(Context context)
    {
        // create a new DatabaseOpenHelper
        databaseOpenHelper =
                new DatabaseOpenHelper(context, DATABASE_NAME, null, 1);
    } // end DatabaseConnector constructor

    // open the database connection
    public void open() throws SQLException
    {
        // create or open a database for reading/writing

        database = databaseOpenHelper.getWritableDatabase();
        database.execSQL("PRAGMA foreign_keys=ON"); //THIS LINE IS USELESS WHEN PLACED ANYWHERE ELSE!!!!

    } // end method open

    // close the database connection
    public void close()
    {
        if (database != null)
            database.close(); // close the database connection
    } // end method close
/*
    public void insertDummyValues(){//very slow insertion, only benefit is when populating for testing purposes, one line needs to be uncommented
        ContentValues dummyAccount = new ContentValues();
        dummyAccount.put("fName", "Tariq");
        dummyAccount.put("mName", "Ali");
        dummyAccount.put("lName", "Damati");
        dummyAccount.put("UAE_ID", "UID1");
        dummyAccount.put("email", "td94");
        dummyAccount.put("password", "td94");


        open(); // open the database
        database.insert("accounts", null, dummyAccount);

        // insert city names
        String[] cities = new String[]{"Dubai", "Sharjah","Abu Dhabi"};
        for (int i = 0; i < cities.length; i++) {
            ContentValues city = new ContentValues();
            city.put("cityName", cities[i]);
            database.insert("cities", null, city);
        }

        close(); // close the database
    }

*/

    //*************************************************"accounts" table***********************************************************************************



    // inserts a new account in the database
    public void insertAccount(String fName, String mName, String lName, String email, String password)
    {
        ContentValues newAccount = new ContentValues();

        newAccount.put("fName", fName);
        newAccount.put("mName", mName);
        newAccount.put("lName", lName);

        newAccount.put("email", email);
        newAccount.put("password", password);


        open(); // open the database
        database.insert("accounts", null, newAccount);
        close(); // close the database
    } // end method insertAccount


    /*
    // update existing account in the database
    public void updateAccount(long id, String fName, String mName, String lName, String UAE_ID, String email, String password)
    {
        ContentValues editAccount = new ContentValues();
        editAccount.put("fName", fName);
        editAccount.put("mName", mName);
        editAccount.put("lName", lName);
        editAccount.put("UAE_ID", UAE_ID);
        editAccount.put("email", email);
        editAccount.put("password", password);

        open(); // open the database
        database.update("accounts", editAccount, "_id=" + id, null);
        close(); // close the database
    } // end method updateAccount
*/

    // return a Cursor with all account information in the database
    public Cursor getAllAccounts()
    {
        return database.query("accounts", new String[] {"_id", "lName"},
                null, null, null, null, "lName");
    } // end method getAllAccounts



    // get a Cursor containing all information about the account specified by the given id
    public Cursor getOneAccount(String email)
    {
        Cursor testCursor;
        testCursor= database.query(
                "accounts", null, "email='" + email+"'", null, null, null, null);
        return testCursor;
    } // end method getOnContact

    // delete the account specified
    public void deleteAccount(long id)
    {
        open(); // open the database
        database.delete("accounts", "_id=" + id, null);
        close(); // close the database
    } // end method deleteAccount

    //check if email already exits in database
    public Boolean checkEmail(String email) {

        open();
        Cursor c = database.rawQuery("Select * from accounts where email=?",new String[]{email});
        if (c.getCount() > 0) {
            close();
            return true;
        } else {
            close();
            return false;
        }
    }

    //check if email and password exist in database
    public Boolean matchEmailPassword(String email, String password){
        open();
        Cursor cc=database.rawQuery("select * from accounts where email=? and password=?",new String[]{email,password});
        if(cc.getCount()>0){
            close();
            return true;
        }
        else{
            close();
            return false;
        }
    }




    private class DatabaseOpenHelper extends SQLiteOpenHelper
    {
        // public constructor
        public DatabaseOpenHelper(Context context, String name,
                                  CursorFactory factory, int version)
        {
            super(context, name, factory, version);
        } // end DatabaseOpenHelper constructor

        // creates the contacts table when the database is created
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            // query to create a new table named accounts
           /* String createQuery = "CREATE TABLE accounts" +
                    "(_id integer primary key autoincrement," +
                    "fName TEXT, mName TEXT, lName TEXT, UAE_ID TEXT, email TEXT, password TEXT);";
                               //  db.execSQL(createQuery); // execute the query
*/          //another way, both produce the same result(thank god!)

            // yes, it's useless here, I tried
            if (!db.isReadOnly()) {
                db.execSQL("PRAGMA foreign_keys=ON;");
            }
            //creating table for patient's accounts
            db.execSQL("Create table accounts(_id integer primary key autoincrement , fName text, mName text, lName text, email text, password text)");




        } // end method onCreate

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion,
                              int newVersion)
        {
        }

        // end method onUpgrade
    } // end class DatabaseOpenHelper
} // end class DatabaseConnector
