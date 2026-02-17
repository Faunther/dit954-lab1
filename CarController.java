import vehicles.Car;
import vehicles.Saab95;
import vehicles.Scania;
import vehicles.Volvo240;

import javax.swing.*;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;

    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    CarBrandWorkshop<Volvo240> volvoWorkshop = new CarBrandWorkshop<>();

    // methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        Volvo240 v = new Volvo240();
        v.getPoint().y = 300;
        cc.cars.add(v);

        Saab95 s95 = new Saab95();
        s95.getPoint().y = 150;
        cc.cars.add(s95);

        Scania scan = new Scania();
        scan.getPoint().y = 250;
        cc.cars.add(scan);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    /*
     * Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Can not remove cars while looping - needs intermediate tracking
            ArrayList<Car> carsToRemove = new ArrayList<>();
            
            for (Car car : cars) {
                car.move();

                var cp = car.getPoint();
                int x = (int) Math.round(cp.getX());
                int y = (int) Math.round(cp.getY());
                // Assumes all cars' rendered size is equal to that of the volvo's
                if (x < 0.0
                        || x + frame.drawPanel.getCarWidth(car) > frame.drawPanel.getSize().width
                        || y < 0.0
                        || y + frame.drawPanel.getCarHeight(car) > frame.drawPanel.getSize().height) {
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();

                    var pos = car.getPoint();
                    pos.x = Math.clamp(pos.x, 1,
                            frame.drawPanel.getSize().width - frame.drawPanel.getCarWidth(car) - 1);
                    pos.y = Math.clamp(pos.y, 1,
                            frame.drawPanel.getSize().height - frame.drawPanel.getCarHeight(car) - 1);
                }

                if(car instanceof Volvo240) {
                    Rectangle cr = new Rectangle(
                        new Point((int)Math.round(cp.getX()), (int)Math.round(cp.getY())),
                        frame.drawPanel.getCarSize(car)
                    );
                    Rectangle wr = new Rectangle(frame.drawPanel.volvoWorkshopPoint, frame.drawPanel.getWorkshopSize());
                    if(cr.intersects(wr)) {
                        // Transfer ownership to the workshop (and also stop rendering)
                        volvoWorkshop.acceptCar((Volvo240)car);
                        carsToRemove.add(car);
                    }
                }
            }

            for (Car car : carsToRemove) {
                cars.remove(car);
            }
            
            frame.drawPanel.setCars(cars);
            // repaint() calls the paintComponent method of the panel
            frame.drawPanel.repaint();
        }
    }

    public void startCars() {
        for (Car car : cars) {
            car.startEngine();
        }
    }

    public void stopCars() {
        for (Car car : cars) {
            car.stopEngine();
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    // Calls the brake method for each car once
    void brake(int amount) {
        double amountNormalized = ((double) amount) / 100;
        for (Car car : cars) {
            car.brake(amountNormalized);
        }
    }

    void turboOn() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Car car : cars) {
            if (car instanceof Saab95) {
                ((Saab95) car).setTurboOff();
            }
        }
    }

    public void liftBed() {
        for (Car car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).rampUp();
            }
        }
    }

    public void lowerBed() {
        for (Car car : cars) {
            if (car instanceof Scania) {
                ((Scania) car).rampDown();
            }
        }
    }

}
