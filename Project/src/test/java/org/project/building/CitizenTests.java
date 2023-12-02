package org.project.building;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.project.model.resource.Citizen;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CitizenTests {
    Citizen citizen;

    @BeforeEach
    void setupTest() {
        this.citizen = new Citizen();
    }

    @Test
    void testEquals() {
        Citizen testCitizen = new Citizen();
        assertEquals(this.citizen, testCitizen);
    }
}
