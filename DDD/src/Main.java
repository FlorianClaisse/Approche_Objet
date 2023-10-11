import Decorator.*;
import Decorator.Creme.VanilleDecorator;
import Decorator.Fruit.*;
import Decorator.Gateau.*;
import Decorator.Supplements.AmandesDecorator;
import Decorator.Supplements.ChantillyDecorator;
import Decorator.Supplements.MeringueDecorator;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        GateauInterface tarte = new MeringueDecorator(new PommeDecorator(new Tarte(null)));
        GateauInterface choux = new AmandesDecorator(new ChantillyDecorator(new VanilleDecorator(new Choux(null))));

        System.out.println(tarte.getName());
        System.out.println(choux.getName());
    }
}