package com.example.bakersandconfectionarymanagementservicesapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.widget.Toast;

public class User_Table extends SQLiteOpenHelper
{
    Context context;
    private static String DATABASE_NAME="UserDetailsDb35.db";
    private static int DATABASE_VERSION=35;
    //String firstName, String lastName, String email, String password
    private static String createTableQuery="create table UserTable(id INTEGER PRIMARY KEY,firstName TEXT ,lastName TEXT,email TEXT,password TEXT)";

    public User_Table(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=  context;
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            db.execSQL(createTableQuery);
        }
        catch(Exception exp)
        {

            Toast.makeText(context,exp.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS UserTable" );
        // Create tables again
        onCreate(db);

    }
    public void addUser(Users user)
    {
        //String firstName, String lastName, String email, String password
        SQLiteDatabase db = this.getWritableDatabase(); // Use getWritableDatabase() instead of getReadableDatabase() to be able to write data

        ContentValues values = new ContentValues();
        values.put("firstName", user.firstName);
        values.put("lastName", user.lastName);
        values.put("email", user.email);
        values.put("password", user.password);

// Define the SQL query with placeholders for values
        String sql = "INSERT INTO UserTable (firstName, lastName, email, password) VALUES (?, ?, ?, ?)";

// Use a prepared statement to execute the query
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindString(1, user.firstName); // Bind values to placeholders
        statement.bindString(2, user.lastName);
        statement.bindString(3, user.email);
        statement.bindString(4, user.password);

// Execute the statement
        long rowId = statement.executeInsert();

// Close the statement and the database connection
        statement.close();
        db.close();

    }
    public boolean checkUserExist(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase(); // Use getReadableDatabase() since we're only reading data
        String sql = "SELECT * FROM UserTable WHERE email = ? AND password = ?";

        // Use a prepared statement to execute the query
        Cursor res = db.rawQuery(sql, new String[]{email, password});

        int count = res.getCount();

        // Close the cursor and the database connection
        res.close();
        db.close();

        return count > 0;//true or false
    }
    public boolean checkEmailExist(String email)
    {
        SQLiteDatabase db = this.getReadableDatabase(); // Use getReadableDatabase() since we're only reading data
        String sql = "SELECT * FROM UserTable WHERE email = ?";

        // Use a prepared statement to execute the query
        SQLiteStatement statement = db.compileStatement(sql);
        statement.bindString(1, email); // Bind values to placeholders


        // Execute the statement
        Cursor res = db.rawQuery(sql, null);

        int count = res.getCount();

        // Close the cursor and the database connection
        res.close();
        statement.close();
        db.close();

        return count > 0;
    }


}
