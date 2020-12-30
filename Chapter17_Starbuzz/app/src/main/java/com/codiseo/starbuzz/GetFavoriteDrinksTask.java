package com.codiseo.starbuzz;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class GetFavoriteDrinksTask extends AsyncTask<Void, Void, Boolean> {

    private TopLevelActivity topLevelActivity;
    private Cursor favoritesCursor;
    ListView listFavorites;

    public GetFavoriteDrinksTask(TopLevelActivity topLevelActivity, ListView listFavorites) {
        this.topLevelActivity = topLevelActivity;
        this.listFavorites = listFavorites;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(topLevelActivity);
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            favoritesCursor = db.query("DRINK", new String[]{"_id", "NAME"}, "FAVORITE = 1", null, null, null, null);
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success) {
            CursorAdapter favoriteAdapter = new SimpleCursorAdapter(topLevelActivity,
                    android.R.layout.simple_list_item_1,
                    favoritesCursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1, 0}
            );
            listFavorites.setAdapter(favoriteAdapter);
        } else {
            Toast toast = Toast.makeText(topLevelActivity, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
