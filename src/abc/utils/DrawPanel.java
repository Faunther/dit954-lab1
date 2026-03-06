package src.abc.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import pics.PicsLoader;
import src.abc.IRenderDataContainer;
import src.abc.RenderData;

// This panel represents the animated part of the view with the car images.
public class DrawPanel extends JPanel {
    private HashMap<String, Image> m_imageDictonary; // Renderable object
    private IRenderDataContainer m_renderDataSource;

    // Initializes the panel and reads the images
    public DrawPanel(IRenderDataContainer renderdataSrc, Dimension size) {
        m_renderDataSource = renderdataSrc;
        m_imageDictonary = new HashMap<>();
        this.setDoubleBuffered(true);
        this.setPreferredSize(size);
        this.setBackground(Color.green);
    }

    public void loadImageEntry(String id, String path) {
        if (this.m_imageDictonary.containsKey(id)) {
            System.out.println("Initiated a \"loadImageEntry\" twice for: " + id);
            // should we do something if an entry already has an image
        }

        // Print an error message in case file is not found with a try/catch block
        try {
            System.out.println("Loading image entry: " + id + " - " + path);
            BufferedImage img = PicsLoader.LoadImage(path);
            this.m_imageDictonary.put(id, img);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ArrayList<RenderData> renderObjects = m_renderDataSource.getRenderObjects();
        for (RenderData renderObject : renderObjects) {
            String id = renderObject.getId();
            Image image = m_imageDictonary.get(id);
            Point pos = renderObject.getPos();
            g.drawImage(image, ((int) pos.x), ((int) pos.y), null);
        }
    }

}
