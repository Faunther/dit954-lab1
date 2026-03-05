package pics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PicsLoader {
    public static BufferedImage LoadImage(String name) throws IOException {
        BufferedImage image = ImageIO.read(PicsLoader.class.getResourceAsStream(name));

        return image;
    }
}
