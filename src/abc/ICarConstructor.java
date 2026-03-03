package src.abc;

import src.CarBrandWorkshop;
import src.vehicles.Car;

public interface ICarConstructor<T extends Car> {
    T makeCar(int x, int y);
    CarBrandWorkshop<T> makeWorkshop(int capactiy);
}
