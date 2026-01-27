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

}
