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
        ArrayList<RenderData> objectsToRender = new ArrayList<>();
        for (CarSystem<?> carSystem : m_CarSystems) {
            objectsToRender.addAll(carSystem.getRenderData());
        }
        return objectsToRender;
    }

}
