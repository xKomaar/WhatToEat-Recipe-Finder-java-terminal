package pl.whatToEat.Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.ArrayList;

public final class RecipeJsonParser {

    private static RecipeJsonParser instance;
    private final ArrayList<RecipeModel> recipeList;

    private RecipeJsonParser() {
        recipeList = new ArrayList<>();

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/main/resources/recipes.json"));
            JSONArray recipeJsonArray = (JSONArray)obj;

            for (Object recipe : recipeJsonArray) {
                JSONObject recipeJson = (JSONObject)recipe;

                String title = (String) recipeJson.get("title");
                String instructions = (String) recipeJson.get("instructions");
                instructions = instructions.substring(0, instructions.length()/2); //instructions in recipes.json are duplicated

                JSONArray ingredientJsonArray = (JSONArray) recipeJson.get("ingredients");
                ArrayList<String> ingredientList = new ArrayList<>();
                for (Object ingredient : ingredientJsonArray) {
                    ingredientList.add((String)ingredient);
                }
                recipeList.add(new RecipeModel(title, ingredientList, instructions));
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<RecipeModel> getRecipeList() {
        if(instance == null) {
            instance = new RecipeJsonParser();
        }
        return instance.recipeList;
    }
}
