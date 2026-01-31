import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.Color;

public class ScaniaTest {
    @Test
    public void testRaiseLower() {
        Scania s = new Scania();

        s.incrementSpeed(2);
        assertThrows(Error.class, () -> {
            s.setBedAngle(10);
        });
        s.setBedAngle(0);

        s.decrementSpeed(2);
        s.setBedAngle(10);
        assertThrows(Error.class, () -> {
            s.incrementSpeed(2);
        });
        s.setBedAngle(0);
        s.incrementSpeed(2);
    }
}
