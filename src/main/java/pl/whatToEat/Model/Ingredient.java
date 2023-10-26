package pl.whatToEat.Model;

public class Ingredient {
    private final String name;
    private boolean available;

    public Ingredient(String name) {
        this.name = name;
        this.available = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", available=" + available +
                '}';
    }
}
