package com.codiseo.starbuzz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK_ID = "drinkId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        //Get the drink from the intent
        int drinkId = (Integer) getIntent().getExtras().get(EXTRA_DRINK_ID);
        Drink drink = Drink.drinks[drinkId];

        //Populate the drink image
        ImageView photoView = findViewById(R.id.photo);
        photoView.setImageResource(drink.getImageResourceId());
        photoView.setContentDescription(drink.getName());

        //Populate the drink name
        TextView nameView = findViewById(R.id.name);
        nameView.setText(drink.getName());

        //Populate the drink description
        TextView descriptionView = findViewById(R.id.description);
        descriptionView.setText(drink.getDescription());
    }
}