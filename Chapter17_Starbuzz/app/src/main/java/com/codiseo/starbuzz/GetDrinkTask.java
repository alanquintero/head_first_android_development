package com.codiseo.starbuzz;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GetDrinkTask extends AsyncTask<Integer, Void, Drink> {

    private DrinkActivity drinkActivity;

    public GetDrinkTask(DrinkActivity drinkActivity) {
        this.drinkActivity = drinkActivity;
    }

    @Override
    protected Drink doInBackground(Integer... drinks) {
        Drink drink = null;
        int drinkId = drinks[0];

        //Create a cursor
        SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(drinkActivity);
        try {
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();

            Cursor cursor = db.query("DRINK", new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "FAVORITE"},
                    "_id = ?", new String[]{Integer.toString(drinkId)}, null, null, null);

            //Move to the first record in the cursor
            if (cursor.moveToFirst()) {
                // Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(1);
                int photoId = cursor.getInt(2);
                boolean isFavorite = (cursor.getInt(3) == 1);

                drink = new Drink(nameText, descriptionText, photoId, isFavorite);
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {
            return null;
        }
        return drink;
    }

    @Override
    protected void onPostExecute(Drink drink) {
        if (drink != null) {
            //Populate the drink image
            ImageView photoView = drinkActivity.findViewById(R.id.photo);
            photoView.setImageResource(drink.getPhotoId());
            photoView.setContentDescription(drink.getName());

            //Populate the drink name
            TextView nameView = drinkActivity.findViewById(R.id.name);
            nameView.setText(drink.getName());

            //Populate the drink description
            TextView descriptionView = drinkActivity.findViewById(R.id.description);
            descriptionView.setText(drink.getDescription());

            //Check/uncheck the favorite checkbox
            CheckBox favorite = drinkActivity.findViewById(R.id.favorite);
            favorite.setChecked(drink.isFavorite());
        } else {
            Toast toast = Toast.makeText(drinkActivity, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
