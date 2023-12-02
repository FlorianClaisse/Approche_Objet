package org.project.resources;

import org.junit.jupiter.api.Test;
import org.project.model.resource.Material;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.project.model.resource.Material.Type.*;

public class MaterialTests {

    @Test
    void testEquals() {
        for (int i = 0; i < values().length; i++) {
            assertEquals(new Material(values()[i]), new Material(values()[i]));
        }
    }

    @Test
    void testNotEquals() {
        assertNotEquals(new Material(FOOD), new Material(WOOD));
        assertNotEquals(new Material(IRON), new Material(TOOLS));
    }
}
