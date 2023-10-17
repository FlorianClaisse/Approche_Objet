/*import Decorator.*;
import Decorator.Creme.VanilleDecorator;
import Decorator.Fruit.*;
import Decorator.Gateau.*;
import Decorator.Supplements.AmandesDecorator;
import Decorator.Supplements.ChantillyDecorator;
import Decorator.Supplements.MeringueDecorator;*/

import Composite.Fruit.Pomme;
import Composite.GateauComposite;
import Composite.Gateau.Tarte;
import Composite.Supplements.Chantilly;
import Composite.Supplements.Meringue;
import Decorator.Gateau.Gateau;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        /*Recette tarte = new MeringueDecorator(new PommeDecorator(new Tarte(null)));
        Recette choux = new AmandesDecorator(new ChantillyDecorator(new VanilleDecorator(new Choux(null))));*/

        /*System.out.println(tarte.getName());
        System.out.println(choux.getName());*/

        GateauComposite gateau = new GateauComposite();
        gateau.addFils(new Tarte());
        gateau.addFils(new Pomme());
        gateau.addFils(new Chantilly());

        System.out.println(gateau.getName());
    }
}