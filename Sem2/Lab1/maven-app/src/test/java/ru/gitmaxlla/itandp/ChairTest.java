package ru.gitmaxlla.itandp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ChairTest {
    private Chair obj_chair = new Chair(123, 123, 123, 123, 123, 123, "Something", Furniture.CONDITION.UNSPECIFIED, false, 2, 130.0);

    @Test
    public void seatHeightCheck() {
        double seatHeightValue = obj_chair.getHeight() - obj_chair.getBackHeight(); 
        assertTrue(seatHeightValue == obj_chair.getSeatHeight());
    }

    @Test
    public void rollableInfluenceCheck() {
        double immobilityScore = obj_chair.getImmobilityScore();
        obj_chair.setRollable(true);
        assertTrue(obj_chair.getImmobilityScore() == immobilityScore / 2);
    }
}
