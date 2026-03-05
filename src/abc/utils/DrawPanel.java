package src.abc.utils;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

import pics.PicsLoader;
import src.abc.IRenderDataContainer;
import src.abc.RenderData;
import src.vehicles.Car;
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
        if (this.m_imageDictonary.containsKey(id)){
            System.out.println("Initiated a \"render resource\" twice for: " + id);
            // should we do something if an entry already has an image
        }

        // Print an error message in case file is not found with a try/catch block
        try {
            // path reference: "pics/Saab95.jpg"

            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to
            // pics.
            // if you are starting in IntelliJ.
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
            String id =  renderObject.getId();
            Image image = m_imageDictonary.get(id);
            Point2D.Double pos = renderObject.getPos();
            g.drawImage(image, ((int) pos.x), ((int) pos.y), null);
        }
    }

}
