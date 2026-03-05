package src.abc;

import src.CarBrandWorkshop;
import src.vehicles.Car;
import src.vehicles.Saab95;

import java.util.ArrayList;

public class Model {
    protected ArrayList<CarSystem<?>> m_CarSystems = new ArrayList<>();

    protected Saab95System m_Saab95System = new Saab95System<>();

    public void addSystem(CarSystem carSystem) {
        m_CarSystems.add(carSystem);
    }

    public void registerSubscribers(Publisher pub) {
        for (CarSystem<?> s : m_CarSystems) {
            pub.addSubscriber(s);
        }
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

    public void addCar() {
        String modelName;
    }

    public void addCar(String modelName) {
        if (modelName == "Saab95") {
            Saab95 s = new Saab95();
            m_Saab95System.addCar(s);
        }
    }
}

// public class MyModel extends Model {
// protected MyCarSystem m_MyCarSystem = new MyCarSystem<>();

// MyModel(){
// super();
// this.m_CarSystems.add(m_CarSystems);
// }

// @Override
// public void addCar(String modelName) {
// if(modelName == "MyCar") {
// MyCar c new MyCar();
// m_MyCarSystem.addCar(c);
// return;
// }

// super.addCar(modelName);
// }
// }