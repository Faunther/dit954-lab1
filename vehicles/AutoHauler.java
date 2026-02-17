package vehicles;

import java.util.Stack;

import vehicles.loadable.IRamp;
import vehicles.loadable.LoadedCarsOnRamp;

public class AutoHauler extends Car implements IRamp {
    private boolean m_rampIsDown = false;
    private Stack<Car> m_loadedCars = new Stack<Car>();

    public static class AutoHaulerData extends CarHaulerData {
        public AutoHaulerData() {
            m_nrDoors = 2;
            m_enginePower = 700;
            m_modelName = "AutoHauler3000";
            m_maxCarsLoaded = 5;
        }
    }

    private static AutoHaulerData g_instance = new AutoHaulerData();

    public AutoHauler() {
        m_carData = g_instance;
        stopEngine();
    }

    @Override
    public void rampDown() {
        if (this.m_currentSpeed != 0)
            throw new Error("can not move ramp while moving");
        m_rampIsDown = true;

    }

    @Override
    public void rampUp() {
        m_rampIsDown = false;

    }

    @Override
    public boolean getRampIsDown() {
        return m_rampIsDown;
    }

    public void loadCar(Car car) {
        if (car instanceof LoadedCarsOnRamp)
            throw new Error("can not load another Car that implements ILoadingBed");

        if (!m_rampIsDown)
            throw new Error("can not load car while ramp is up");

        if (car.m_position.distanceSq(this.m_position) > 10 * 10)
            throw new Error("car is too far away to be loaded");

        if (m_loadedCars.size() >= AutoHauler.g_instance.m_maxCarsLoaded)
            throw new Error("maximum number of cars already loaded");

        m_loadedCars.add(car);
    }

    /**
     * Unloads the last unloaded car
     * 
     * @return Car, or null if no cars are currently loaded
     */
    public Car unloadCar() {
        if (!m_rampIsDown)
            throw new Error("can not unload car while ramp is up");

        if (m_loadedCars.empty())
            return null;

        return m_loadedCars.pop();
    }

    private void updateLoadedCarsPosition() {
        for (Car car : m_loadedCars) {
            car.m_position = this.m_position;
            car.m_direction = this.m_direction;
        }
    }

    public void move() {
        super.move();
        updateLoadedCarsPosition();
    }

    public void turnLeft() {
        super.turnLeft();
        updateLoadedCarsPosition();
    }

    public void turnRight() {
        super.turnRight();
        updateLoadedCarsPosition();
    }

    @Override
    public double speedFactor() {
        if (this.m_rampIsDown)
            throw new Error("Can not change speed while ramp is lowered");
        return m_carData.getEnginePower() * 0.001;
    }
}
