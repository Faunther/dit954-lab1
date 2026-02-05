
public class BrandWorkshop<TBrand extends Car> extends Workshop {
    public BrandWorkshop(int capacity) {
        super(capacity);
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
