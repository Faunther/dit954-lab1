import org.junit.Test;

import static org.junit.Assert.*;

public class Volvo240Test {

    @Test
    public void speedFactor() {
        Volvo240 volvo = new Volvo240();
        final double expectedEnginePower = 100;
        final double expectedTrimFactor = 1.25;
        final double expectedSpeedFactor = expectedEnginePower * expectedTrimFactor * 0.01;
        assertEquals(expectedSpeedFactor, volvo.speedFactor(), 0.001);
    }

    @Test
    public void incrementSpeed() {
        Volvo240 volvo = new Volvo240();

        // speed should be 0 with "freshly made" car
        assertEquals(0, volvo.getCurrentSpeed(), 0.001);

        // PREP: ======================================================================================================
        // expected constants
        final double expectedEnginePwr = volvo.getEnginePower();
        final double expectedSpeedFactor = volvo.speedFactor();

        // expected formula
        // m_currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,m_carData.getEnginePower());


        // speed should increase by the amount times the speedFactor.
        double amount = 10.0;
        final double expectedSpeed = Math.min(
            volvo.getCurrentSpeed() + expectedSpeedFactor * amount,
            expectedEnginePwr
        );
        volvo.incrementSpeed(amount);
        assertEquals(expectedSpeed, volvo.getCurrentSpeed(), 0.001);

        // Speed should not have been affected by incrementing with a negative value
        amount = -amount;
        volvo.incrementSpeed(amount);
        assertEquals(expectedSpeed, volvo.getCurrentSpeed(), 0.001);

        // Speed should remain constant after incrementing with 0
        volvo.incrementSpeed(0);
        assertEquals(expectedSpeed, volvo.getCurrentSpeed(), 0.001);

        // speed should be equal to or below its engine power
        {
            /*
            * to make sure we reach the limit, we repeatedly increment by
            * Double.MAX_VALUE
            *
            * We could've used "expectedEnginePwr / speedFactor", but we might
            * get an overflow value (big value -> negative value) if speedFactor is very small
            *
            * It probably won't happen, but better to be safe than sorry in a test.
            * */

            double remainder = expectedEnginePwr - volvo.getCurrentSpeed();
            double maxPossibleIncrement = Double.MAX_VALUE * expectedSpeedFactor;
            do {
                volvo.incrementSpeed(Double.MAX_VALUE);
                remainder -= maxPossibleIncrement;
            } while (maxPossibleIncrement < remainder);
        }
        assertEquals(expectedEnginePwr, volvo.getCurrentSpeed(), 0.001);
    }

    @Test
    public void decrementSpeed() {
        Volvo240 volvo = new Volvo240();

        // # Base case (new car, no changes)
        assertEquals(0, volvo.getCurrentSpeed(), 0.001);

        // speed can't decrease below 0.
        double amount = 10.0;
        volvo.decrementSpeed(amount);
        assertEquals(0, volvo.getCurrentSpeed(), 0.001);

        // PREP: ======================================================================================================
        // expected constants
        final double expectedEnginePwr = volvo.getEnginePower();
        final double expectedSpeedFactor = volvo.speedFactor();

        // expected formula:
        // m_currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);

        // increment car to max speed for tests
        {
            double remainder = expectedEnginePwr;
            double maxPossibleIncrement = Double.MAX_VALUE * expectedSpeedFactor;
            do {
                volvo.incrementSpeed(Double.MAX_VALUE);
                remainder -= maxPossibleIncrement;
            } while (maxPossibleIncrement < remainder);
        }


        // # Decrease by "normal" amount
        double expectedSpeed = Math.max(
            volvo.getCurrentSpeed() - expectedSpeedFactor * amount,
            0
        );
        volvo.decrementSpeed(amount);
        assertEquals(expectedSpeed, volvo.getCurrentSpeed(), 0.001);

        // decrement by negative amount
        amount = -amount;
        volvo.decrementSpeed(amount);
        assertEquals(expectedSpeed, volvo.getCurrentSpeed(), 0.001);

        // decrement by 0
        volvo.decrementSpeed(0);
        assertEquals(expectedSpeed, volvo.getCurrentSpeed(), 0.001);
    }
}