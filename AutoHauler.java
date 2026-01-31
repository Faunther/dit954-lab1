import java.util.Stack;

public class AutoHauler extends Car {
    private boolean m_rampIsDown = false;
    private Stack<Car> m_loadedCars = new Stack<Car>();

    public static class AutoHaulerData extends CarData {
        public int m_maxCarsLoaded;

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

    /// TODO: Since this works differently to the Scania (discrete vs continous
    /// beds) it is not easily combined into a singel superclass. However, it may be
    /// possible to add a "loadable" interface or maybe abstract class that at least
    /// standardizes checking if ramp/bed is up before moving etc. Could also maybe
    /// be used to check the size of the Car-instance (AutoHauler can not load cars
    /// with loading capabilities of themselves?)

    public boolean getRampIsDown() {
        return m_rampIsDown;
    }

    public void setRampIsDown(boolean isDown) {
        if (this.m_currentSpeed != 0 && isDown)
            throw new Error("can not move ramp while moving");

        m_rampIsDown = isDown;
    }

    public void loadCar(Car car) {
        if (car instanceof AutoHauler)
            throw new Error("can not load another AutoHauler onto this auto hauler");

        if (!m_rampIsDown)
            throw new Error("can not load car while ramp is up");
        if (car.m_position.distance(this.m_position) > 10)
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
        // TODO: Should this be dependant on the load or something?
        return m_carData.getEnginePower() * 0.001;
    }

    @Override
    public void incrementSpeed(double amount) {
        if (this.m_rampIsDown)
            throw new Error("Can not change speed while ramp is lowered");

        // TODO: Trucks can only go like 80 kph? Dependant on number of cars
        // transported?
        amount = Math.max(0, amount);
        m_currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, m_carData.getEnginePower());
    }

    @Override
    public void decrementSpeed(double amount) {
        amount = Math.max(0, amount);
        m_currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

}
