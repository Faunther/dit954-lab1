import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BrandWorkshop<TBrand extends Car>{
    private int m_carCapacity = 0;
    protected Map<Integer, Car> m_garage = new HashMap<>();

    public BrandWorkshop() {
        this(0);
    }

    public BrandWorkshop(int carCapacity) {
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


    Integer acceptCar(TBrand car) {
        if (car == null || !canAcceptCar())
            return null;

        Integer id = car.hashCode();
        this.m_garage.put(id, car);

        return id;
    }


    TBrand retrieveCar(Integer id) {
        if (id == null || !m_garage.containsKey(id))
            return null;

        Car car = m_garage.remove(id);
        return (TBrand)car;
    }
}
