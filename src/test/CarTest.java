package src.test;

import org.junit.Test;

import src.vehicles.Saab95;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class CarTest {
    @Test
    public void testMoveAndTurn() {
        double testspeed = 0;
        Point testpoint = new Point(0, 0);
        /*
         * skapar en saab för att testa car Classen
         * eftersom car är abstract kan vi inte skapa en
         */
        Saab95 s = new Saab95();

        s.startEngine();
        s.turnRight();

        testspeed = 0.1;
        assertEquals(s.getCurrentSpeed(), testspeed, 0.0001);

        s.gas(10);
        s.move();
        assertFalse(s.getPoint().equals(testpoint));

        testpoint.y += s.getCurrentSpeed();
        assertEquals(s.getPoint(), testpoint);

        s.brake(3);
        s.move();
        testpoint.y += s.getCurrentSpeed();
        assertEquals(s.getPoint(), testpoint);

        s.turnLeft();
        s.move();
        testpoint.x += s.getCurrentSpeed();
        assertEquals(s.getPoint(), testpoint);

        s.turnRight();
        s.move();
        testpoint.y += s.getCurrentSpeed();
        assertEquals(s.getPoint(), testpoint);

    }

}
