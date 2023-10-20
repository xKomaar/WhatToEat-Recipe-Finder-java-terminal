package pl.whatToEat.Model;

import java.util.ArrayList;

public record RecipeModel(String title, ArrayList<String> ingredientList, String instructions) {

}


