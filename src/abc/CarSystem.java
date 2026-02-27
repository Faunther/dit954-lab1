package src.abc;

import java.util.ArrayList;

import src.vehicles.Car;

public class CarSystem<T extends Car> implements IDriveSubscriber {
    protected ArrayList<T> m_cars = new ArrayList<>();

    public void addCar(T car) {
        m_cars.add(car);
    }

    public void removeCar(T car) {m_cars.remove(car);}

    public ArrayList<T> getCars() {
        return m_cars;
    }

    public void onGasEvent(int gasAmount) {
        for (Car car: m_cars){
            car.gas(gasAmount);
        }
    }

    public void onBrakeEvent(int brakeAmount) {
        for (Car car: m_cars){
            car.brake(brakeAmount);
        }
    }

    public void onStopEngineEvent() {
        for (Car car: m_cars){
            car.stopEngine();
        }
    }

    public void onStartEngineEvent() {
        for (Car car: m_cars){
            car.startEngine();
        }
    }

    public void onMoveTickEvent() {
        for (Car car: m_cars){
            updateCar(car);
        }

    }
    protected void updateCar(Car car){
        //double carWidth = frame.drawPanel.getCarWidth(car);
        //double carHeight= frame.drawPanel.getCarHeight(car);
        double carWidth = 5.0;
        double carHeight= 5.0;

        //double panelWidth = frame.drawPanel.getSize().width;
        //double padelHeight = frame.drawPanel.getSize().height;
        double panelWidth = 500;
        double padelHeight = 500;

        car.move();
        var cp = car.getPoint();
        int x = (int) Math.round(cp.getX());
        int y = (int) Math.round(cp.getY());
        // Assumes all cars' rendered size is equal to that of the volvo's
        if (x < 0.0
                || x + carWidth > panelWidth
                || y < 0.0
                || y + carHeight >  padelHeight) {
            car.stopEngine();
            car.turnLeft();
            car.turnLeft();
            car.startEngine();

            var pos = car.getPoint();
            pos.x = Math.clamp(pos.x, 1,
                    panelWidth - carWidth - 1);
            pos.y = Math.clamp(pos.y, 1,
                    padelHeight - carHeight - 1);
        }
    }







}
