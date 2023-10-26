package pl.whatToEat.Model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeMap;

public class RecipeCalculator {

    ArrayList<Recipe> recipeList;

    private final int threshold;

    public RecipeCalculator(int threshold) {

        recipeList = RecipeJsonParser.getRecipeList();
        this.threshold = threshold;
    }

    public TreeMap<Integer, Recipe> calculateRecipes(ArrayList<String> ingredientsList) {
        TreeMap<Integer, Recipe> recipeMap = new TreeMap<>(Comparator.reverseOrder());

        for(Recipe recipe : recipeList) {
            int validIngredientCount = 0;
            int recipeIngredientCount = recipe.ingredientList().size();
            for(String usrIng : ingredientsList) {
                String userIngredient = usrIng.toLowerCase();

                for(Ingredient recIng : recipe.ingredientList()) {
                    String recipeIngredientName = recIng.getName().toLowerCase();

                    if (recipeIngredientName.contains(userIngredient)) {
                        int index = recipeIngredientName.indexOf(userIngredient);
                        //check if ingredient is exact (unlikely)
                        if (recipeIngredientName.equals(userIngredient)) {
                            validIngredientCount++;
                        }
                        else {
                            //extract the word from the recipe ingredient depending on
                            //if there is a space in user ingredient
                            StringBuilder recipeWord = new StringBuilder();
                            if(userIngredient.contains(" ")) {
                                for(int i = index; i < index+userIngredient.length(); i++) {
                                    recipeWord.append(recipeIngredientName.charAt(i));
                                }
                            }
                            else {
                                for(int i = index; i < recipeIngredientName.length() && recipeIngredientName.charAt(i) != ' '; i++) {
                                    recipeWord.append(recipeIngredientName.charAt(i));
                                }
                            }

                            //check if this word is equal to user ingredient OR check if the recipe ingredient is in plural form
                            //(if you type "egg" - "eggs" will be found, for "tomato" - "tomatoes" will be found
                            //all these checks are to stop returning words like "eggplant", when "egg" was typed
                            if (recipeWord.toString().equals(userIngredient)
                                    || ((recipeWord.length() == userIngredient.length()+1 || recipeWord.length() == userIngredient.length()+2)
                                    && recipeWord.charAt(recipeWord.length()-1) == 's'))
                            {
                                validIngredientCount++;
                                recIng.setAvailable(true);
                            }
                        }
                    }
                }
            }
            int matchPercent = (validIngredientCount/recipeIngredientCount*100);
            if(matchPercent > threshold) {
                recipeMap.put(matchPercent, recipe);
            }
        }
        return recipeMap;
    }
}
