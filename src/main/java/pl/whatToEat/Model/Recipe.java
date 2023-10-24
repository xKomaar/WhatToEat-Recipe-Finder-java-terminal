package pl.whatToEat.Model;

import java.util.ArrayList;

public record Recipe(String title, ArrayList<String> ingredientList, String instructions) {

}


