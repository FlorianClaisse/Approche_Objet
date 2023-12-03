package org.project.resources;

import org.junit.jupiter.api.Test;
import org.project.model.resource.Citizen;
import org.project.model.resource.Gold;
import org.project.model.resource.Material;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.project.model.resource.Material.Type.*;
import static org.project.model.resource.ResourceFactory.*;

public class ResourcesFactoryTests {

    @Test
    void testCitizen() {
        Citizen testCitizen = citizen();
        assertEquals(testCitizen.getTypeName(), new Citizen().getTypeName());
        assertEquals(testCitizen, new Citizen());
    }

    @Test
    void testGold() {
        Gold testGold = gold();
        assertEquals(testGold.getTypeName(), new Gold().getTypeName());
        assertEquals(testGold, new Gold());
    }

    @Test
    void testFood() {
        Material value = new Material(FOOD);
        Material testValue = food();
        assertEquals(testValue.getPrice(), value.getPrice());
        assertEquals(testValue.getTypeName(), value.getTypeName());
        assertEquals(testValue, value);
    }

    @Test
    void testWood() {
        Material value = new Material(WOOD);
        Material testValue = wood();
        assertEquals(testValue.getPrice(), value.getPrice());
        assertEquals(testValue.getTypeName(), value.getTypeName());
        assertEquals(testValue, value);
    }

    @Test
    void testStone() {
        Material value = new Material(STONE);
        Material testValue = stone();
        assertEquals(testValue.getPrice(), value.getPrice());
        assertEquals(testValue.getTypeName(), value.getTypeName());
        assertEquals(testValue, value);
    }

    @Test
    void testCoal() {
        Material value = new Material(COAL);
        Material testValue = coal();
        assertEquals(testValue.getPrice(), value.getPrice());
        assertEquals(testValue.getTypeName(), value.getTypeName());
        assertEquals(testValue, value);
    }

    @Test
    void testIron() {
        Material value = new Material(IRON);
        Material testValue = iron();
        assertEquals(testValue.getPrice(), value.getPrice());
        assertEquals(testValue.getTypeName(), value.getTypeName());
        assertEquals(testValue, value);
    }

    @Test
    void testSteel() {
        Material value = new Material(STEEL);
        Material testValue = steel();
        assertEquals(testValue.getPrice(), value.getPrice());
        assertEquals(testValue.getTypeName(), value.getTypeName());
        assertEquals(testValue, value);
    }

    @Test
    void testCement() {
        Material value = new Material(CEMENT);
        Material testValue = cement();
        assertEquals(testValue.getPrice(), value.getPrice());
        assertEquals(testValue.getTypeName(), value.getTypeName());
        assertEquals(testValue, value);
    }

    @Test
    void testLumber() {
        Material value = new Material(LUMBER);
        Material testValue = lumber();
        assertEquals(testValue.getPrice(), value.getPrice());
        assertEquals(testValue.getTypeName(), value.getTypeName());
        assertEquals(testValue, value);
    }

    @Test
    void testTools() {
        Material value = new Material(TOOLS);
        Material testValue = tools();
        assertEquals(testValue.getPrice(), value.getPrice());
        assertEquals(testValue.getTypeName(), value.getTypeName());
        assertEquals(testValue, value);
    }
}
