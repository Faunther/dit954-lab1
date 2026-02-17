import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

import vehicles.Car;
// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel {

    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300, 300);

    ArrayList<Car> cars = new ArrayList<>();
    HashMap<String, Image> carImages = new HashMap<>();

    void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to
            // pics.
            // if you are starting in IntelliJ.

            BufferedImage volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            carImages.put("Volvo240", volvoImage);
            BufferedImage saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            carImages.put("Saab95", saabImage);
            BufferedImage scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            carImages.put("Scania", scaniaImage);
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
        for (Car car : cars) {
            Image image = carImages.get(car.getModelName());
            var cp = car.getPoint();
            g.drawImage(image, ((int) cp.x), ((int) cp.y), null);
        }
    }

    public int getCarWidth(Car car) {
        return carImages.get(car.getModelName()).getWidth(null);
    }

    public int getCarHeight(Car car) {
        return carImages.get(car.getModelName()).getHeight(null);
    }

    public Dimension getCarSize(Car car) {
        Image ci = carImages.get(car.getModelName());
        return new Dimension(ci.getWidth(null), ci.getHeight(null));
    }
    
    public Dimension getWorkshopSize() {
        return new Dimension(volvoWorkshopImage.getWidth(), volvoWorkshopImage.getHeight());
    }
}
