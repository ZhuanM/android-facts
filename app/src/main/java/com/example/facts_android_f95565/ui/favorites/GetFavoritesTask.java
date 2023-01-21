package com.example.facts_android_f95565.ui.favorites;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import com.example.facts_android_f95565.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class GetFavoritesTask extends AsyncTask<Void, Void, List<String>> {

    private DatabaseHelper dbHelper;
    private FavoritesAdapter favoritesAdapter;
    private String currentUsername;

    public GetFavoritesTask(DatabaseHelper db, FavoritesAdapter adapter, String username) {
        favoritesAdapter = adapter;
        currentUsername = username;
        dbHelper = db;
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query("facts", new String[]{"fact"}, "username=?", new String[]{currentUsername}, null, null, null);

        List<String> favoriteFacts = new ArrayList<>();
        while (cursor.moveToNext()) {
            int index = cursor.getColumnIndex("fact");
            if (index != -1) {
                String fact = cursor.getString(index);
                favoriteFacts.add(fact);
            }
        }

        cursor.close();
        db.close();

        return favoriteFacts;
    }

    @Override
    protected void onPostExecute(List<String> facts) {
        super.onPostExecute(facts);
        favoritesAdapter.setData(facts);
    }
}