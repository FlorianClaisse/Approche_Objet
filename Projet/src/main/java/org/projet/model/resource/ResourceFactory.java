package org.projet.model.resource;

import static org.projet.model.resource.Material.Type.*;

/**
 * The class used to create the different resources
 */
public final class ResourceFactory {
    public static Gold gold() { return new Gold(); }

    public static Material food() { return new Material(FOOD); }
    public static Material wood() { return new Material(WOOD); }
    public static Material stone() { return new Material(STONE); }
    public static Material coal() { return new Material(COAL); }
    public static Material iron() { return new Material(IRON); }
    public static Material steel() { return new Material(STEEL); }
    public static Material cement() { return new Material(CEMENT); }
    public static Material lumber() { return new Material(LUMBER); }
    public static Material tools() { return new Material(TOOLS); }

    public static Material material(Material.Type type) {
        return switch (type) {
            case STONE -> stone();
            case WOOD -> wood();
            case COAL -> coal();
            case FOOD -> food();
            case IRON -> iron();
            case STEEL -> steel();
            case TOOLS -> tools();
            case CEMENT -> cement();
            case LUMBER -> lumber();
        };
    }
}
