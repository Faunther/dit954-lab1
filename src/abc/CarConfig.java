package src.abc;

import pics.PicsLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class CarConfig implements IConfig {
    HashMap<String, Dimension> m_dimensions;

    public CarConfig() throws IOException {
        m_dimensions = new HashMap<>();

        // load images for their sizes,
        // store them under their corresponding entries
        // throw away the images.
        appendImageEntry("Volvo240", "Volvo240.jpg");
        appendImageEntry("Saab95", "Saab95.jpg");
        appendImageEntry("Scania", "Scania.jpg");
        appendImageEntry("VolvoBrand", "VolvoBrand.jpg");

        // will need to differentiate between "window" and "drawArea" dimensions
        m_dimensions.put("WorldMap", new Dimension(800, 800));
    }

    private void appendImageEntry(String entry, String path) throws IOException {
        BufferedImage volvoImg = PicsLoader.LoadImage(path);
        Dimension volvoDim = new Dimension(volvoImg.getWidth(), volvoImg.getHeight());
        m_dimensions.put(entry, volvoDim);
    }

    @Override
    public Dimension getDimension(String entryName) {
        return m_dimensions.get(entryName);
    }

    @Override
    public void setWorldSize(int x, int y) {
        m_dimensions.get("WorldMap").setSize(x, y);
    }
}
