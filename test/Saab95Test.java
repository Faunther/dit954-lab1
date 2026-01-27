import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.Color;

public class Saab95Test {

    @Test
    public void testSetColor(){
        Saab95 s = new Saab95();
        s.setColor(Color.CYAN);
        assertEquals(Color.CYAN, s.getColor());
    }

    @Test
    public void getCurrentSpeed(){
        Saab95 s = new Saab95();
        Assert.assertEquals(s.m_currentSpeed, s.getCurrentSpeed(), 0.001);
    }

    @Test
    public void getEnginePower(){
        Saab95 s = new Saab95();
        assertEquals(s.getEnginePower(), s.m_carData.m_enginePower, 0.001);
    }

    @Test
    public void getNrDoors(){
        Saab95 s = new Saab95();
        assertEquals(s.m_carData.m_nrDoors, s.getNrDoors());
    }

    @Test
    public void testTurbo() {
        Saab95 s = new Saab95();
        assertEquals(false, s.turboOn);

        // Speed factor without turbo
        assertEquals(125 * 0.01, s.speedFactor(), 0.001);

        s.setTurboOn();
        assertEquals(s.turboOn, true);
        s.setTurboOff();
        assertEquals(s.turboOn, false);
        s.setTurboOn();

        // Make sure turbo status actually changes speedFactor
        assertEquals(125 * 0.01 * 1.3, s.speedFactor(), 0.001);

        assertEquals(0, s.getCurrentSpeed(), 0.001);

        // Test increment speed (with turbo)
        s.incrementSpeed(2);
        assertEquals(2 * 125 * 0.01 * 1.3, s.getCurrentSpeed(), 0.001);

        // Test decrement speed (with turbo)
        s.decrementSpeed(1);
        assertEquals(1 * 125 * 0.01 * 1.3, s.getCurrentSpeed(), 0.001);
    }
}
