package Decorator;

public abstract class Decorator implements Recette {
    private Recette recetteDecorated;

    public Decorator(Recette recetteDecorated) {
        this.recetteDecorated = recetteDecorated;
    }

    @Override public String getName() { return recetteDecorated.getName(); }
    @Override public Ingredients getIngredients() { return recetteDecorated.getIngredients(); }
}
