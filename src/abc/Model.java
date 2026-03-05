package src.abc;

import src.CarBrandWorkshop;
import src.vehicles.Saab95;

import java.awt.Point;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Model {
    protected ArrayList<CarSystem<?>> m_CarSystems = new ArrayList<>();

    protected ArrayList<String> m_availableModels = new ArrayList<>();
    protected Saab95System<Saab95> m_Saab95System = new Saab95System<>();

    public Model() {
        m_availableModels.add("Saab95");
        m_CarSystems.add(m_Saab95System);
    }

    public void registerSubscribers(Publisher pub) {
        for (CarSystem<?> s : m_CarSystems) {
            pub.addSubscriber(s);
        }
    }

    public ArrayList<RenderData> getRenderObjects() {
        ArrayList<RenderData> objectsToRender = new ArrayList<>();
        for (CarSystem<?> carSystem : m_CarSystems) {
            objectsToRender.addAll(carSystem.getRenderData());
        }
        return objectsToRender;
    }

    public void addCar() throws NoSuchAlgorithmException {
        Point pos = new Point();
        addCar(pos);
    }

    public void addCar(Point pos) throws NoSuchAlgorithmException {
        int idx = java.security.SecureRandom.getInstanceStrong().nextInt(m_availableModels.size());
        String modelName = m_availableModels.get(idx);
        addCar(modelName, pos);
    }

    public void addCar(String modelName) {
        Point pos = new Point();
        this.addCar(modelName, pos);
    }

    public void addCar(String modelName, Point pos) {
        if (modelName == "Saab95") {
            Saab95 s = new Saab95();
            m_Saab95System.addCar(s);
        }
    }

    public void addCarBrandWorkshop(String modelName, Point pos) {
        if (modelName == "Saab95") {
            CarBrandWorkshop<Saab95> ws = new CarBrandWorkshop<Saab95>();
            m_Saab95System.addWorkshop(pos, ws);
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