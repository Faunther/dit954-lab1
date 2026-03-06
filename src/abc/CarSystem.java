package src.abc;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import src.CarBrandWorkshop;
import src.abc.utils.Pair;
import src.vehicles.Car;

public class CarSystem<T extends Car> implements IDriveSubscriber {
    protected ArrayList<T> m_cars = new ArrayList<>();
    // implemented as Point, instead of Point2D.Double
    protected ArrayList<Pair<Point, CarBrandWorkshop<T>>> m_workshops = new ArrayList<>();

    public void addCar(T car) {
        m_cars.add(car);
    }

    public void addWorkshop(Point p, CarBrandWorkshop<T> ws) {
        m_workshops.add(new Pair<>(p, ws));
    }

    public ArrayList<RenderData> getRenderData() {
        ArrayList<RenderData> objectsToRender = new ArrayList<>();

        for (Car car : this.m_cars) {
            RenderData rd = new RenderData(car.getModelName(), car.getPoint());
            objectsToRender.add(rd);
        }
        for (Pair<Point, CarBrandWorkshop<T>> placedWorkshop : m_workshops) {
            Point pos = placedWorkshop.getFirst();
            Point2D.Double posD = new Point2D.Double(pos.getX(), pos.getY());
            CarBrandWorkshop<T> workshop = placedWorkshop.getSecond();

            RenderData rd = new RenderData(workshop.getWorkshopName(), posD);
            objectsToRender.add(rd);
        }
        return objectsToRender;
    }

    public void removeCar(T car) {
        m_cars.remove(car);
    }

    public void removeFirstCar() {
        if (!m_cars.isEmpty()) {
            m_cars.removeFirst();
        }
    }

    public ArrayList<T> getCars() {
        return m_cars;
    }

    public void onGasEvent(int gasAmount) {
        for (Car car : m_cars) {
            car.gas(gasAmount);
        }
    }

    public void onBrakeEvent(int brakeAmount) {
        for (Car car : m_cars) {
            car.brake(brakeAmount);
        }
    }

    public void onStopEngineEvent() {
        for (Car car : m_cars) {
            car.stopEngine();
        }
    }

    public void onStartEngineEvent() {
        for (Car car : m_cars) {
            car.startEngine();
        }
    }

    public void onMoveTickEvent() {
        for (Car car : m_cars) {
            updateCar(car);
        }

    }

    protected void updateCar(Car car) {
        CarConfig cfg = CarConfig.getSingleton();

        Dimension carDim = cfg.getDimension(car.getModelName());
        // double carWidth = frame.drawPanel.getCarWidth(car);
        // double carHeight= frame.drawPanel.getCarHeight(car);
        //double carWidth = 5.0;
        //double carHeight = 5.0;

        Dimension panelDim = cfg.getDimension("WorldMap");
        // double panelWidth = frame.drawPanel.getSize().width;
        // double padelHeight = frame.drawPanel.getSize().height;
        //double panelWidth = 500;
        //double padelHeight = 500;

        car.move();
        var cp = car.getPoint();
        int x = (int) Math.round(cp.getX());
        int y = (int) Math.round(cp.getY());
        // Assumes all cars' rendered size is equal to that of the volvo's
        if (x < 0.0
                || x + carDim.getWidth() > panelDim.getWidth()
                || y < 0.0
                || y + carDim.getHeight() > panelDim.getHeight()) {
            car.stopEngine();
            car.turnLeft();
            car.turnLeft();
            car.startEngine();

            var pos = car.getPoint();
            pos.x = Math.clamp(pos.x, 1,
                    panelDim.getWidth() - carDim.getWidth() - 1);
            pos.y = Math.clamp(pos.y, 1,
                    panelDim.getWidth() - carDim.getHeight() - 1);
        }
    }

}
