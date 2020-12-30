package com.codiseo.starbuzz;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.Toast;

public class UpdateDrinkTask extends AsyncTask<Integer, Void, Boolean> {

    private DrinkActivity drinkActivity;
    private ContentValues drinkValues;

    public UpdateDrinkTask(DrinkActivity drinkActivity) {
        this.drinkActivity = drinkActivity;
    }

    @Override
    protected void onPreExecute() {
        CheckBox favorite = drinkActivity.findViewById(R.id.favorite);
        drinkValues = new ContentValues();
        drinkValues.put("FAVORITE", favorite.isChecked());
    }

    @Override
    protected Boolean doInBackground(Integer... drinks) {
        int drinkId = drinks[0];
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(drinkActivity);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getWritableDatabase();
            db.update("DRINK", drinkValues, "_id = ?", new String[]{Integer.toString(drinkId)});
            db.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (!success) {
            Toast toast = Toast.makeText(drinkActivity, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}

