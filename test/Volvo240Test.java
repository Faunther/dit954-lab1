import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.*;

public class Volvo240Test {

    @Test
    @DisplayName("Test speed Factor")
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

        // m_currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,m_carData.getEnginePower());
        final double amount = 5.0;
        final double expectedSpeed = Math.min(
            volvo.getCurrentSpeed() + volvo.speedFactor() * amount,
            volvo.getEnginePower()
        );
        // do we increase with the
        volvo.incrementSpeed(amount);
        assertEquals(expectedSpeed, volvo.getCurrentSpeed(), 0.001);
    }

    @Test
    public void decrementSpeed() {
    }
}