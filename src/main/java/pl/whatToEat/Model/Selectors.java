package pl.whatToEat.Model;

public class Selectors {

    public enum InputIngredientsSelectors {
        ADD_INGREDIENT,
        DELETE_INGREDIENT,
        SHOW_RECIPES,
        SETTINGS
    }

    public enum SpoonPosition {
        RIGHT,
        MIDDLE,
        LEFT
    }

    public enum ChosenSetting {
        SORT,
        THRESHOLD
    }

    public static int matchThreshold = 50;

    public static boolean sortByIngredientCountFirst = false;
}
