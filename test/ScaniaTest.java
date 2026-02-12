import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class ScaniaTest {
    @Test
    public void testCantLowerBedWhileMoving() {
        Scania s = new Scania();

        // Try to lower bed while moving
        s.incrementSpeed(2);
        assertThrows(Error.class, () -> {
            s.setBedAngle(10);
        });
        assertThrows(Error.class, s::rampDown);

        try {
            s.setBedAngle(0);
            s.decrementSpeed(2);
        }
        catch (Error e) {
            fail("Should be able to set bed angle to 0 while moving");
        }

    }

    @Test
    public void testCantMoveWithLoweredBed() {
        Scania s = new Scania();

        s.setBedAngle(10);
        assertThrows(Error.class, () -> {
            s.incrementSpeed(2);
        });
        s.rampDown();
        assertThrows(Error.class, () -> {
            s.incrementSpeed(2);
        });

        try {
            s.rampUp();
            s.incrementSpeed(2);
        }
        catch (Error E){
            fail("Should be able to increase speed while ramp is up!");
        }
    }

    @Test
    public void testRaiseLimits() {
        Scania s = new Scania();
        // Test if bed angle is clamped between 0 and 70.

        double delta = 0.0001f;
        s.setBedAngle(-10.0);
        assertEquals(0.0, s.getCurrentBedAngle(), delta);

        s.setBedAngle(80.0);
        assertEquals(70.0, s.getCurrentBedAngle(), delta);
    }

}
