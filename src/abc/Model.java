package src.abc;

import src.CarBrandWorkshop;
import src.vehicles.Car;

import java.util.ArrayList;

public class Model {
    protected ArrayList<CarSystem> m_CarSystems = new ArrayList<>();
    protected ArrayList<CarBrandWorkshop> m_Workshops = new ArrayList<>();


    public void addSystem(CarSystem carSystem) {
        m_CarSystems.add(carSystem);
    }

    public void addCarBrandWorkshop(CarBrandWorkshop carBrandWorkshop) {
        m_Workshops.add(carBrandWorkshop);
    }

    public ArrayList<RenderData> getRenderObjects() {
        ArrayList<RenderData> myObjects = new ArrayList<>();

        for (CarSystem<?> carSystem : m_CarSystems) {
            var cars = carSystem.getCars();
            for (Car car : cars) {
                RenderData rd = new RenderData(car.getModelName(), car.getPoint());
                myObjects.add(rd);
            }
        }

        return myObjects;
    }

}
