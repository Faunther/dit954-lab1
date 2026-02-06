import java.awt.geom.Point2D;
import java.util.Stack;

public class CarFerry extends Boat implements Ramp{
    private boolean m_IsRampDown;
    Stack<Car> m_loadedCars;

    public CarFerry(){
        super();
        m_IsRampDown = false;
        m_loadedCars = new Stack<Car>();
    }

    @Override
    public String getName() {
        return "CarFarry1000";
    }
    @Override
    public int getEnginePower() {
        return 1000;
    }

    @Override
    public int getMaxSpeed() {
        return 50;
    }

    @Override
    public void rampDown() {
        m_IsRampDown = false;

    }

    @Override
    public void rampUpp() {
        m_IsRampDown = true;
    }

    @Override
    public int numberOffCarsOnRamp() {
        return m_loadedCars.size();
    }

    @Override
    public int getMaxCars() {
        return 25;
    }

    @Override
    public void loadCarOnToRamp(Car car) {
        if (car instanceof ILoadingBed)
            throw new Error("can not load another Car that implements ILoadingBed");

        if (!m_IsRampDown)
            throw new Error("can not load car while ramp is up");

        if (car.m_position.distanceSq(this.m_position) > 10*10)
            throw new Error("car is too far away to be loaded");

        if (numberOffCarsOnRamp() >= getMaxCars())
            throw new Error("maximum number of cars already loaded");

        m_loadedCars.add(car);

        }


}



