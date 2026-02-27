package src.abc;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;


public class CarView {
    private Publisher m_publisher;

    private HashMap<String, String> m_imageDictonary = new HashMap<>(); // Renderable object

    public void addModelImage(String car, String carImage) {

        // TODO actually load the images
        m_imageDictonary.put(car,carImage);

    }

    public void addSubscriber(Publisher pub) {
        this.m_publisher = pub;

    }


}
