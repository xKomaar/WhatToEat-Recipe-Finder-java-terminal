package pl.whatToEat.Model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.InputStreamReader;
import java.util.ArrayList;

public final class RecipeJsonParser {

    private static RecipeJsonParser instance;
    private final ArrayList<Recipe> recipeList;

    private RecipeJsonParser() {
        recipeList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new InputStreamReader(this.getClass().getResourceAsStream("/recipes.json")));
            JSONArray recipeJsonArray = (JSONArray)obj;

            for (Object recipe : recipeJsonArray) {
                JSONObject recipeJson = (JSONObject)recipe;

                String title = (String) recipeJson.get("title");
                String instructions = (String) recipeJson.get("instructions");

                JSONArray ingredientJsonArray = (JSONArray) recipeJson.get("ingredients");
                ArrayList<Ingredient> ingredientList = new ArrayList<>();
                for (Object ingredient : ingredientJsonArray) {
                    ingredientList.add(new Ingredient((String)ingredient));
                }
                if(!ingredientList.isEmpty() && !title.equals("") && !instructions.equals("")) {
                    //some instructions in recipes.json are duplicated, some have only parts duplicated, and it is impossible
                    //to fix them all, but this is the best it can be
                    if (isDuplicated(instructions)) {
                        instructions = instructions.substring(0, instructions.length()/2);
                    }
                    if(instructions.length() < 2400) {
                        recipeList.add(new Recipe(title, ingredientList, instructions));
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isDuplicated(String s) {
        String s1 = s.substring(0, (s.length()/2)+1);
        String s2 = s.substring((s.length()/2));

        if(s1.contains(s2)) {
            return true;
        }

        String s1OnlyLetters="", s2OnlyLetters="";
        for(int i=0; i< s1.length(); i++) {
            if(s1.charAt(i) != ' ' && s1.charAt(i) != ',' && s1.charAt(i) != '.' && s1.charAt(i) != ';'
                    && s1.charAt(i) != ')' && s1.charAt(i) != '(' && s1.charAt(i) != '}' && s1.charAt(i) != '{'
                    && s1.charAt(i) != '=' && s1.charAt(i) != '+' && s1.charAt(i) != '-' && s1.charAt(i) != '"') {
                s1OnlyLetters += s1.charAt(i);
            }
        }
        for(int i=0; i< s2.length(); i++) {
            if(s2.charAt(i) != ' ' && s2.charAt(i) != ',' && s2.charAt(i) != '.' && s2.charAt(i) != ';'
                    && s2.charAt(i) != ')' && s2.charAt(i) != '(' && s2.charAt(i) != '}' && s2.charAt(i) != '{'
                    && s2.charAt(i) != '=' && s2.charAt(i) != '+' && s2.charAt(i) != ' ' && s2.charAt(i) != '"') {
                s2OnlyLetters += s2.charAt(i);
            }
        }
        if(s1OnlyLetters.contains(s2OnlyLetters)) {
            return true;
        }

        return false;
    }
    //Singleton
    public static ArrayList<Recipe> getRecipeList() {
        if(instance == null) {
            instance = new RecipeJsonParser();
        }
        return instance.recipeList;
    }
}
