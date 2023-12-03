package org.project.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuantityTests {

    @Test
    void testEquals() {
        Quantity value = new Quantity();
        Quantity testValue = new Quantity();
        assertEquals(value, testValue);

        value.set(5);
        assertNotEquals(value, testValue);

        testValue.add(5);
        assertEquals(value, testValue);
    }

    @Test
    void testValidValue() {
        Quantity quantity = new Quantity();
        assertEquals(quantity.get(), 0);

        quantity.set(78);
        assertEquals(quantity.get(), 78);

        quantity.add();
        assertEquals(quantity.get(), 79);

        quantity.add(10);
        assertEquals(quantity.get(), 89);
        quantity.add(0);
        assertEquals(quantity.get(), 89);

        quantity.remove();
        assertEquals(quantity.get(), 88);

        quantity.remove(70);
        assertEquals(quantity.get(), 18);
        quantity.remove(0);
        assertEquals(quantity.get(), 18);
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> { new Quantity().remove(-10); } );
        assertThrows(IllegalArgumentException.class, () -> { new Quantity().remove(-1); } );
        assertThrows(IllegalArgumentException.class, () -> { new Quantity().add(-10); } );
        assertThrows(IllegalArgumentException.class, () -> { new Quantity().add(-1); } );
    }
}
