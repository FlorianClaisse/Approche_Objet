import Decorator.Creme.*;
import Decorator.Fruit.*;
import Decorator.Gateau.*;
import Decorator.Supplements.*;
import Composite.*;

import BuilderAndObserver.*;
import AbstractFactory.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // exampleWithDecorator();
        // exampleWithCompositeWithoutBuilder();
        // exampleWithCompositeAndBuilder();
        // exampleWithCompositeAndFactory();
        exampleWithCompositeAndBuilderAndObserve();
    }

    public static void exampleWithDecorator() {
        // Tarte aux pommes
        Decorator.Recette tarte1 = new PommesDecorator(new Tarte(null));
        System.out.println(tarte1.getName());

        // Tarte aux abricots meringuée
        Decorator.Recette tarte2 = new MeringueDecorator(new AbricotsDecorator(new Tarte(null)));
        System.out.println(tarte2.getName());

        // Chou au chocolat
        Decorator.Recette chou1 = new ChocolatDecorator(new Chou(null));
        System.out.println(chou1.getName());

        // Chou à la vanille suppléments chantilly et amandes
        Decorator.Recette chou2 = new AmandesDecorator(new ChantillyDecorator(new VanilleDecorator(new Chou(null))));
        System.out.println(chou2.getName());
    }

    public static void exampleWithCompositeWithoutBuilder() {
        // Tarte (feuilletée) aux pommes
        GateauComposite gateau1 = new GateauComposite();
        gateau1.addFils(new Pate(Pate.Type.FEUILLETEE));
        gateau1.addFils(new Fruit(Fruit.Type.POMME));
        System.out.println(gateau1.getName());

        // Tarte (brisée) aux abricots meringuée
        GateauComposite gateau2 = new GateauComposite();
        gateau2.addFils(new Pate(Pate.Type.BRISEE));
        gateau2.addFils(new Fruit(Fruit.Type.ABRICOT));
        gateau2.addFils(new Supplement(Supplement.Type.MERINGUE));
        System.out.println(gateau2.getName());

        // Chou au chocolat
        GateauComposite gateau3 = new GateauComposite();
        gateau3.addFils(new Pate(Pate.Type.CHOUX));
        gateau3.addFils(new Creme(Creme.Type.CHOCOLAT));
        System.out.println(gateau3.getName());

        // Chou à la vanille suppléments chantilly et amandes
        GateauComposite gateau4 = new GateauComposite();
        gateau4.addFils(new Pate(Pate.Type.CHOUX));
        gateau4.addFils(new Creme(Creme.Type.VANILLE));
        gateau4.addFils(new Supplement(Supplement.Type.CHANTILLY));
        gateau4.addFils(new Supplement(Supplement.Type.AMANDE));
        System.out.println(gateau4.getName());
    }

    public static void exampleWithCompositeAndBuilder() {
        Patissier patissier = new Patissier();

        // Tarte (feuilletée) aux pommes
        patissier.makeTarteFeuilleteePommes();
        System.out.println(patissier.getBuilder().getGateau().getName());

        // Tarte (feuilletée) aux abricots meringuée
        patissier.makeTarteFeuilleteeAbricotsMeringue();
        System.out.println(patissier.getBuilder().getGateau().getName());

        // Chou au chocolat
        patissier.makeChouChocolat();
        System.out.println(patissier.getBuilder().getGateau().getName());

        // Chou à la vanille suppléments chantilly et amandes
        patissier.makeChouVanilleChantillyAmandes();
        System.out.println(patissier.getBuilder().getGateau().getName());
    }

    public static void exampleWithCompositeAndFactory() {
        GateauFactory factory1 = new TarteFeuilleteePommeFactory();
        GateauFactory factory2 = new TarteBriseeAbricotMeringueFactory();
        GateauFactory factory3 = new ChouChocolat();
        GateauFactory factory4 = new ChouVanilleChantillyAmandeFactory();

        // Tarte (feuilletée) aux pommes
        Recette tarte1 = factory1.make();
        System.out.println(tarte1.getName());

        // Tarte (brisée) aux abricots meringuée
        Recette tarte2 = factory2.make();
        System.out.println(tarte2.getName());

        // Chou au chocolat
        Recette chou1 = factory3.make();
        System.out.println(chou1.getName());

        // Chou à la vanille suppléments chantilly et amandes
        Recette chou2 = factory4.make();
        System.out.println(chou2.getName());
    }

    public static void exampleWithCompositeAndBuilderAndObserve() {
        Boulangerie boulangerie = new Boulangerie(new ArrayList<String>(
            Arrays.asList(
                "Tarte Feuilletée Pommes",
                "Tarte Feuilletée Abricots",
                "Tarte Feuilletée Pommes Meringue",
                "Tarte Feuilletée Abricots Meringue",
                "Chou Chocolat",
                "Chou Vanille",
                "Chou Chocolat Noisettes",
                "Chou Vanille Chantilly")), 5);

        Vendeur vendeur = boulangerie.getVendeur();
        ArrayList<Pair<Recette, Integer>> sacClient = new ArrayList<>();

        sacClient.add(vendeur.vente("Chou Chocolat", 10, boulangerie));
        sacClient.add(vendeur.vente("Chou Vanille Amandes", 1, boulangerie)); // Pas proposé --> null
        sacClient.add(vendeur.vente("Tarte Feuilletée Abricots Meringue", 1, boulangerie));
        sacClient.add(vendeur.vente("Chou Chocolat", 7, boulangerie));

        for (var elem: sacClient) {
            if(elem != null)
                System.out.println(elem.getSecond() + " " + elem.getFirst().getName());
        }
    }
}