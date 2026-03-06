package src.abc;

import pics.PicsLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class CarConfig implements IConfig, IWindowResizeSubscriber {
    HashMap<String, Dimension> m_dimensions;

    private static CarConfig g_instance;
    public static CarConfig getSingleton(){ return g_instance; }

    public CarConfig() throws IOException {
        g_instance = this;
        m_dimensions = new HashMap<>();

        // load images for their sizes,
        // store them under their corresponding entries
        // throw away the images.
        appendImageEntry("Volvo240", "Volvo240.jpg");
        appendImageEntry("Saab95", "Saab95.jpg");
        appendImageEntry("Scania", "Scania.jpg");
        appendImageEntry("Volvo240Workshop", "VolvoBrand.jpg");

        final int initWindowWidth = 800;
        final int initWindowHeight = 800;
        final int initWorldWidth = initWindowWidth;
        final int initWorldHeight = initWindowHeight - 240;
        m_dimensions.put("Window", new Dimension(initWindowWidth, initWindowHeight));
        m_dimensions.put("WorldMap", new Dimension(initWorldWidth, initWorldHeight));
    }

    private void appendImageEntry(String entry, String path) throws IOException {
        BufferedImage volvoImg = PicsLoader.LoadImage(path);
        Dimension volvoDim = new Dimension(volvoImg.getWidth(), volvoImg.getHeight());
        m_dimensions.put(entry, volvoDim);
    }

    @Override
    public Dimension getDimension(String entryName) {
        return new Dimension(m_dimensions.get(entryName));
    }

    @Override
    public void resizeTarget(String target, Dimension dim) {
        m_dimensions.get(target).setSize(dim);

    }
}
