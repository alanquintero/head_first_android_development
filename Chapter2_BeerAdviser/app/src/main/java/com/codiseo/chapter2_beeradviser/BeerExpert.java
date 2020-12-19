package com.codiseo.chapter2_beeradviser;

import java.util.ArrayList;
import java.util.List;

public class BeerExpert {
    List<String> getBrands(String color) {
        List<String> brands = new ArrayList<>();
        switch(color) {
            case "light":
                brands.add("Corona");
                brands.add("Coors Light");
                break;
            case "amber":
                brands.add("Jack Amber");
                brands.add("XX Amber");
                break;
            case "brown":
                brands.add("Bitburger");
                brands.add("Lost Coast");
                break;
            case "dark":
                brands.add("Kostritzer");
                brands.add("Goose Island");
                break;
        }
        return brands;
    }
}
