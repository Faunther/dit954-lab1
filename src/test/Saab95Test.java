import org.junit.Assert;
import org.junit.Test;

import src.vehicles.Saab95;

import static org.junit.Assert.*;

import java.awt.Color;

public class Saab95Test {

    @Test
    public void testSetColor() {
        Saab95 s = new Saab95();
        s.setColor(Color.CYAN);
        assertEquals(Color.CYAN, s.getColor());
    }

    @Test
    public void getCurrentSpeed() {
        Saab95 s = new Saab95();
        Assert.assertEquals(s.getCurrentSpeed(), s.getCurrentSpeed(), 0.001);
    }

    @Test
    public void getEnginePower() {
        Saab95 s = new Saab95();
        assertEquals(125, s.getEnginePower(), 0.1);
    }

    @Test
    public void getNrDoors() {
        Saab95 s = new Saab95();
        assertEquals(2, s.getNrDoors());
    }

    @Test
    public void testTurbo() {
        Saab95 s = new Saab95();
        s.startEngine();

        assertEquals(false, s.isTurboOn());

        // Speed factor without turbo
        assertEquals(125 * 0.01, s.speedFactor(), 0.001);

        s.setTurboOn();
        assertEquals(s.isTurboOn(), true);
        s.setTurboOff();
        assertEquals(s.isTurboOn(), false);
        s.setTurboOn();

        // Make sure turbo status actually changes speedFactor
        assertEquals(125 * 0.01 * 1.3, s.speedFactor(), 0.001);

        assertEquals(0.1, s.getCurrentSpeed(), 0.001);

        // Test increment speed (with turbo)
        s.gas(0.5);
        assertEquals(0.1+ 0.5 * 125 * 0.01 * 1.3, s.getCurrentSpeed(), 0.001);

        // Test decrement speed (with turbo)
        s.brake(0.25);
        assertEquals(0.1+ 0.25 * 125 * 0.01 * 1.3, s.getCurrentSpeed(), 0.001);
    }
}
