package org.project.utils;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PairTests {

    @Test
    void testValidValue() {
        Pair<String, Integer> testValue = new Pair<>("Test", 32);
        assertEquals(testValue.getFirst(), "Test");
        assertEquals(testValue.getSecond(), 32);

        testValue.setFirst("New Value");
        assertEquals(testValue.getFirst(), "New Value");
        assertEquals(testValue.getSecond(), 32);

        testValue.setSecond(45);
        assertEquals(testValue.getFirst(), "New Value");
        assertEquals(testValue.getSecond(), 45);
    }
}
