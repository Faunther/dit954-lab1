package abc;

import java.util.ArrayList;

import vehicles.Car;

public class CarSystem<T extends Car> implements IDriveSubscriber {
    protected ArrayList<T> m_cars = new ArrayList<>();

    public void addCar(T car) {
        m_cars.add(car);
    }

    public ArrayList<T> getCars() {
        return m_cars;
    }

    public void onGasEvent(int gasAmount) {
    }

    public void onBrakeEvent(int brakeAmount) {
    }

    public void onStopEngineEvent() {
    }

    public void onStartEngineEvent() {
    }

    public void onMoveTickEvent() {
    }
}
