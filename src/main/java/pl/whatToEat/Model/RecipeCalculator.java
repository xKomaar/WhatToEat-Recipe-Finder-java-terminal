package pl.whatToEat.Model;

import java.util.*;

public class RecipeCalculator {

    ArrayList<Recipe> recipeList;

    public RecipeCalculator() {

        recipeList = RecipeJsonParser.getRecipeList();
    }

    public ArrayList<Recipe> calculateRecipes(ArrayList<String> ingredientsList) {
        ArrayList<Recipe> outputRecipes = new ArrayList<>();

        for(Recipe recipe : recipeList) {
            int validIngredientCount = 0;
            int recipeIngredientCount = recipe.getIngredientList().size();
            for(String usrIng : ingredientsList) {
                String userIngredient = usrIng.toLowerCase();

                for(Ingredient recIng : recipe.getIngredientList()) {
                    String recipeIngredientName = recIng.getName().toLowerCase();

                    if (recipeIngredientName.contains(userIngredient) && !recIng.isAvailable()) {
                        int index = recipeIngredientName.indexOf(userIngredient);
                        //check if ingredient is exact (unlikely)
                        if (recipeIngredientName.equals(userIngredient)) {
                            validIngredientCount++;
                            recIng.setAvailable(true);
                        }
                        else {
                            //extract the word from the recipe ingredient depending on
                            //if there is a space in user ingredient
                            StringBuilder recipeWord = new StringBuilder();
                            if(userIngredient.contains(" ") || userIngredient.contains("-")) {
                                for(int i = index; i < index+userIngredient.length(); i++) {
                                    recipeWord.append(recipeIngredientName.charAt(i));
                                }
                            }
                            else {
                                if(index > 0 && recipeIngredientName.charAt(index-1) == ' ') {
                                    for(int i = index; i < recipeIngredientName.length() && recipeIngredientName.charAt(i) != ' ' && recipeIngredientName.charAt(i) != '-'; i++) {
                                        recipeWord.append(recipeIngredientName.charAt(i));
                                    }
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
            int matchPercent = (int)(((double)validIngredientCount/(double)recipeIngredientCount)*100);
            if(matchPercent >= Selectors.matchThreshold) {
                recipe.setMatchPercent(matchPercent);
                outputRecipes.add(recipe);
            }
        }
        if(Selectors.sortByIngredientCountFirst) {
            //sorting only by ingredient count
            outputRecipes.sort((o1,o2) -> {
                if(o1.getIngredientList().size() > o2.getIngredientList().size()) {
                    return -1;
                }
                else if(o1.getIngredientList().size() == o2.getIngredientList().size()) {
                    if(o1.getMatchPercent() > o2.getMatchPercent()) {
                        return -1;
                    }
                    else if (o1.getMatchPercent() == o2.getMatchPercent()){
                        return 0;
                    }
                    else return 1;
                }
                else return 1;
            });
        }
        else {
            //Sorting the list, example: if both recipes have 100% match, then a recipe is chosen with the most amount of ingredients
            outputRecipes.sort((o1,o2) -> {
                if(o1.getMatchPercent() > o2.getMatchPercent()) {
                    return -1;
                }
                else if(o1.getMatchPercent() == o2.getMatchPercent()) {
                    if(o1.getIngredientList().size() > o2.getIngredientList().size()) {
                        return -1;
                    }
                    else if (o1.getIngredientList().size() == o2.getIngredientList().size()){
                        return 0;
                    }
                    else return 1;
                }
                else return 1;
            });
        }

        return outputRecipes;
    }
}
