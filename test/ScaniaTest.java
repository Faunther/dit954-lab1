import org.junit.Assert;
import org.junit.Test;

import vehicles.Scania;

import static org.junit.Assert.*;

public class ScaniaTest {
    @Test
    public void testRaiseAndLowerBed() {
        Scania s = new Scania();
        
        s.setBedAngle(10);
        assertEquals(10, s.getCurrentBedAngle(), 0.01);
        
        s.setBedAngle(0);        
        assertEquals(0, s.getCurrentBedAngle(), 0.01);

        s.startEngine();
    }
    
    @Test
    public void testCanNotMoveWhileRaised() {
        Scania s = new Scania();
        
        s.setBedAngle(10);
        assertThrows(Error.class, () -> {
            s.startEngine();
        });
        s.setBedAngle(0);
        s.startEngine();
    }
    
    @Test
    public void testCanNotRaiseWhileMoving() {
        Scania s = new Scania();
        s.startEngine();

        // Setting to zero is always ok!
        s.setBedAngle(0);

        assertThrows(Error.class, () -> {
            s.setBedAngle(10);
        });
        s.gas(2);
        assertThrows(Error.class, () -> {
            s.setBedAngle(10);
        });
    }
}
