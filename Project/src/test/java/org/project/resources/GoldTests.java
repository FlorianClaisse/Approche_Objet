package org.project.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.model.resource.Gold;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoldTests {
    Gold gold;

    @BeforeEach
    void setupTest() {
        this.gold = new Gold();
    }

    @Test
    void testEquals() {
        Gold testGold = new Gold();
        assertEquals(this.gold, testGold);
    }
}
