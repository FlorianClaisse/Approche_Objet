package org.project.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.model.resource.Citizen;
import org.project.model.resource.Gold;
import org.project.model.resource.Material;
import org.project.model.resource.Resources;

import static org.junit.jupiter.api.Assertions.*;

public class ResourcesTests {
    Resources resources;

    @BeforeEach
    void setupTest() {
        this.resources = new Resources();
    }

    @Test
    void testInitWithAllResources() {
        this.resources.initWithAllResources();

        assertNotNull(this.resources.get(new Gold()));
        assertEquals(this.resources.get(new Gold()).get(), 0);

        for (Material.Type type: Material.Type.values()) {
            assertNotNull(this.resources.get(new Material(type)));
            assertEquals(this.resources.get(new Material(type)).get(), 0);
        }
    }

    @Test
    void testInitWithAllResourcesNoCitizen() {
        this.resources.initWithAllResources();

        assertNull(this.resources.get(new Citizen()));
    }
}
