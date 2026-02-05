import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Workshop {
    private int m_carCapacity = 0;
    protected Map<Integer, Car> m_garage = new HashMap<>();

    public Workshop() {
        this(0);
    }

    public Workshop(int carCapacity) {
        this.m_carCapacity = carCapacity;
    }

    public int getCarCapacity() {
        return m_carCapacity;
    }

    public int getNumOfCars() {
        return m_garage.size();
    }

    public boolean canAcceptCar() {
        return this.m_garage.size() < this.m_carCapacity;
    }

}