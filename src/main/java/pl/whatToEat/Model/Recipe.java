package pl.whatToEat.Model;

import java.util.ArrayList;

public record Recipe(String title, ArrayList<Ingredient> ingredientList, String instructions) {

}


