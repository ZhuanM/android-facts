package com.example.facts_android_f95565;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "facts.db";

    public DatabaseHelper(Context context) {
        super(context, "facts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(username TEXT PRIMARY KEY, password TEXT)");
        db.execSQL("CREATE TABLE facts(fact TEXT, username TEXT, PRIMARY KEY (fact, username))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS facts");
    }

    public Boolean registerUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("username", username);
        values.put("password", password);

        long result = db.insert("users", null, values);

        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkUsernameExists(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=?", new String[] { username });

        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkLoginParams(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE username=? AND password=?", new String[] { username,password });

        if (cursor.getCount() > 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void saveFact(String fact, String username, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("fact", fact);
        values.put("username", username);

        Boolean factExists = checkIfFactExist(fact, username);
        if (!factExists) {
            db.insert("facts", null, values);
            Toast.makeText(context, "Fact added to favorites!", Toast.LENGTH_SHORT).show();
        }
        else {
            db.delete("facts", "fact = ? AND username = ?", new String[] {fact, username});
            Toast.makeText(context, "Fact removed from favorites!", Toast.LENGTH_SHORT).show();
        }
    }

    private Boolean checkIfFactExist(String fact, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM facts WHERE fact = ? AND username = ?", new String[] {fact, username});
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }
    }
}
