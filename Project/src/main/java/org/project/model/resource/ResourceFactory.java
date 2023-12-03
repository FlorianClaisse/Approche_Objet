package org.project.model.resource;

import static org.project.model.resource.Material.Type.*;

public final class ResourceFactory {

    public static Citizen citizen() { return new Citizen(); }

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
}
