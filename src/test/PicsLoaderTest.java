package src.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import pics.PicsLoader;

public class PicsLoaderTest {
    @Test
    public void testLoadPictureDoesNotFailCompletely() throws IOException {
        PicsLoader.LoadImage("Saab95.jpg");
    }
}
