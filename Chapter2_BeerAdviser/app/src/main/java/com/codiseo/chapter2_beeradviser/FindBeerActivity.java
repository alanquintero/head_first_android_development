package com.codiseo.chapter2_beeradviser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class FindBeerActivity extends AppCompatActivity {

    private BeerExpert beerExpert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    // Called when the button gets clicked
    public void onClickFindBeer(View view) {
        // Get a reference to the TextView
        TextView brands = findViewById(R.id.brands);

        // Get a reference to the Spinner
        Spinner color = findViewById(R.id.color);

        // Get the selected item in the Spinner
        String beerType = String.valueOf(color.getSelectedItem());

        // Display the selected item
        StringBuilder brandsText = new StringBuilder();
        List<String> brandsByBeerType = beerExpert.getBrands(beerType);
        for(String brand : brandsByBeerType) {
            brandsText.append(brand + "\n");
        }
        brands.setText(brandsText);
    }
}