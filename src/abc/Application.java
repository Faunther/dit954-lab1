package src.abc;

import java.awt.Point;

public class Application {
    public static void main(String[] args) throws Exception {
        Publisher pub = new Publisher();
        Model model = new Model();

        model.addCar("Scania", new Point(100, 100));
        model.addCar("Volvo240", new Point(200, 100));
        model.addCar("Saab95", new Point(300, 100));
        model.addCarBrandWorkshop("Volvo240", new Point(100, 100), 2);

        model.registerSubscribers(pub);

        // Used to store as IView
        CarView v = new CarView(model);
        v.addModelImage("Volvo240", "Volvo240.jpg");
        v.addModelImage("Saab95", "Saab95.jpg");
        v.addModelImage("Scania", "Scania.jpg");
        v.addModelImage("Volvo240Workshop", "VolvoBrand.jpg");
        v.addSubscriber(pub); // bind IActionEventHandler to view
        pub.addSubscriber(v); // bind IGameTickSubscriber to Publisher

        System.out.println("Init finished - starting application");
        pub.start();
    }

}
