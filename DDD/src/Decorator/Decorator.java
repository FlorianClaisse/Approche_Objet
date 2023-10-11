package Decorator;

public abstract class Decorator implements GateauInterface {
    private GateauInterface gateauDecorated;

    public Decorator(GateauInterface gateauDecorated) {
        this.gateauDecorated = gateauDecorated;
    }

    @Override public String getName() { return gateauDecorated.getName(); }
    @Override public Ingredients getIngredients() { return gateauDecorated.getIngredients(); }
}
