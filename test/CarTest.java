import org.junit.Assert;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class CarTest {
    @Test
    public void textMoveAndTurn(){
        double testspeed = 0;
        Point2D.Double testpoint = new Point2D.Double(0, 0);

        Saab95 s = new Saab95();
        s.startEngine();
        testspeed = 0.1;
        assertEquals(s.getCurrentSpeed(),testspeed,0.0001);
        s.incrementSpeed(10);
        s.move();
        assertFalse(s.getpoint().equals(testpoint));









    }



}
