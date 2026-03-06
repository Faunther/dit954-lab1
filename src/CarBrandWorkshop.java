package src;

import java.util.HashMap;
import java.util.Map;

import src.vehicles.Car;

public class CarBrandWorkshop<TBrand extends Car> {
    private int m_carCapacity = 0;
    protected Map<Integer, TBrand> m_garage = new HashMap<>();
    private final Class<TBrand> brandClass;

    public String getWorkshopName() {
        return brandClass.getSimpleName() + "Workshop";
    }

    public CarBrandWorkshop() {
        this(0);
    }

    public CarBrandWorkshop(int carCapacity) {
        this.brandClass = (Class<TBrand>) Car.class;
        this.m_carCapacity = carCapacity;
    }

    public CarBrandWorkshop(int carCapacity, Class<TBrand> brandClass) {
        this.brandClass = brandClass;
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

    public Integer acceptCar(TBrand car) {
        if (car == null || !canAcceptCar())
            return null;

        Integer id = car.hashCode();
        this.m_garage.put(id, car);

        return id;
    }

    public TBrand retrieveCar(Integer id) {
        if (id == null || !m_garage.containsKey(id))
            return null;

        return m_garage.remove(id);
    }
}
