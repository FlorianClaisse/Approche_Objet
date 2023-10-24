/*import Decorator.*;
import Decorator.Creme.VanilleDecorator;
import Decorator.Fruit.*;
import Decorator.Gateau.*;
import Decorator.Supplements.AmandesDecorator;
import Decorator.Supplements.ChantillyDecorator;
import Decorator.Supplements.MeringueDecorator;*/

import Builder.GateauDirector;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        /*Recette tarte = new MeringueDecorator(new PommeDecorator(new Tarte(null)));
        Recette choux = new AmandesDecorator(new ChantillyDecorator(new VanilleDecorator(new Choux(null))));*/

        /*System.out.println(tarte.getName());
        System.out.println(choux.getName());*/

        /*GateauComposite gateau = new GateauComposite();
        gateau.addFils(new Tarte());
        gateau.addFils(new Pommes());
        gateau.addFils(new Chantilly());

        System.out.println(gateau.getName());*/

        GateauDirector director = new GateauDirector();
        director.constructChouxVanille();

        System.out.println(director.getBuilder().getGateau().getName());

        director.constructChouxChocolatNoisette();
        System.out.println(director.getBuilder().getGateau().getName());

        director.constructTarteFeuilleteAbricotMeringue();
        System.out.println(director.getBuilder().getGateau().getName());
    }
}