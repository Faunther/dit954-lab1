package src.abc;

import java.awt.Point;

public class Application {
    public static void main(String[] args) {
        Publisher pub = new Publisher();
        Model model = new Model();

        model.addCar("Saab95", new Point(0, 200));
        model.addCar("Saab95", new Point(40, 500));

        model.registerSubscribers(pub);

        // Used to store as IView
        CarView v = new CarView(model);
        v.addModelImage("Saab95", "images/saab95.png");
        v.addSubscriber(pub); // bind IActionEventHandler to view
        pub.addSubscriber(v); // bind IGameTickSubscriber to Publisher

        pub.star();
    }

}
