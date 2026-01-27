import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

public class Saab95Test {

    @Test
    public void testSetColor(){
        Saab95 s = new Saab95();
        s.setColor(Color.CYAN);
        assertEquals(Color.CYAN, s.getColor());
    }

    @Test
    public void testTurbo() {
        Saab95 s = new Saab95();
        s.startEngine();
        assertEquals(s.turboOn, false);

        // Speed factor without turbo
        assertEquals(s.speedFactor(), 125 * 0.01, 0.001);

        s.setTurboOn();
        assertEquals(s.turboOn, true);

        // Make sure turbo status actually changes speedFactor
        assertEquals(s.speedFactor(), 125 * 0.01 * 1.3, 0.001);

        assertEquals(s.getCurrentSpeed(), 0, 0.1);

        // Test increment speed (with turbo)
        s.incrementSpeed(2);
        assertEquals(s.getCurrentSpeed(), 2 * 125 * 0.01 * 1.3, 0.1);

        // Test decrement speed (with turbo)
        s.decrementSpeed(1);
        assertEquals(s.getCurrentSpeed(), 1 * 125 * 0.01 * 1.3, 0.1);
    }
}
