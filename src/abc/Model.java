package src.abc;

import src.CarBrandWorkshop;
import src.vehicles.Volvo240;
import src.vehicles.Saab95;
import src.vehicles.Scania;

import java.awt.Point;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;

public class Model implements IRenderDataContainer, IAddRemoveCarSubscriber {
    protected ArrayList<CarSystem<?>> m_CarSystems = new ArrayList<>();

    protected ArrayList<String> m_availableModels = new ArrayList<>();
    protected Volvo240System<Volvo240> m_Volvo240System = new Volvo240System<>();
    protected Saab95System<Saab95> m_Saab95System = new Saab95System<>();
    protected ScaniaSystem<Scania> m_ScaniaSystem = new ScaniaSystem<>();

    protected CarConfig m_config;

    public Model() {
        m_availableModels.add("Volvo240");
        m_CarSystems.add(m_Volvo240System);

        m_availableModels.add("Saab95");
        m_CarSystems.add(m_Saab95System);

        m_availableModels.add("Scania");
        m_CarSystems.add(m_ScaniaSystem);

        try {
            m_config = new CarConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public IConfig getConfig() {
        return m_config;
    }

    public void registerSubscribers(Publisher pub) {
        for (CarSystem<?> s : m_CarSystems) {
            pub.addSubscriber(s);
        }
        pub.addSubscriber(m_config);
        pub.addSubscriber(this);
    }

    @Override
    public ArrayList<RenderData> getRenderObjects() {
        ArrayList<RenderData> objectsToRender = new ArrayList<>();
        for (CarSystem<?> carSystem : m_CarSystems) {
            objectsToRender.addAll(carSystem.getRenderData());
        }
        return objectsToRender;
    }

    public void addCar() {
        Point pos = new Point();
        addCar(pos);
    }

    public void addCar(Point pos) {
        int idx = new Random().nextInt(m_availableModels.size());
        String modelName = m_availableModels.get(idx);
        try {
            addCar(modelName, pos);
        } catch (Exception e) {
            throw new AssertionError("Model can not be invalid here - chosen from list of available models", e);
        }
    }

    public void addCar(String modelName) throws Exception {
        Point pos = new Point();
        this.addCar(modelName, pos);
    }

    public void addCar(String modelName, Point pos) throws Exception {
        if (modelName == "Volvo240") {
            Volvo240 s = new Volvo240();
            m_Volvo240System.addCar(s);
            return;
        }
        if (modelName == "Saab95") {
            Saab95 s = new Saab95();
            m_Saab95System.addCar(s);
            return;
        }
        if (modelName == "Scania") {
            Scania s = new Scania();
            m_ScaniaSystem.addCar(s);
            return;
        }
        throw new Exception("model not found: " + modelName);
    }

    public void addCarBrandWorkshop(String modelName, Point pos) throws Exception {
        if (modelName == "Volvo240") {
            CarBrandWorkshop<Volvo240> ws = new CarBrandWorkshop<Volvo240>();
            m_Volvo240System.addWorkshop(pos, ws);
            return;
        }
        if (modelName == "Saab95") {
            CarBrandWorkshop<Saab95> ws = new CarBrandWorkshop<Saab95>();
            m_Saab95System.addWorkshop(pos, ws);
            return;
        }
        if (modelName == "Scania") {
            CarBrandWorkshop<Scania> ws = new CarBrandWorkshop<Scania>();
            m_ScaniaSystem.addWorkshop(pos, ws);
            return;
        }

        throw new Exception("model not found: " + modelName);
    }

    // Implements IAddRemoveCarSubscriber:
    public void onAddCar() {
        this.addCar();
    }

    public void onRemoveCar() {
        int numSys = m_CarSystems.size();
        int idx = new Random().nextInt(numSys);
        int counter = 0;
        CarSystem<?> curSys = m_CarSystems.get(idx);
        while (curSys.getCars().isEmpty() && counter < numSys){
            idx = (idx + 1) % numSys;
            counter++;
            // check with next sys
            curSys = m_CarSystems.get(idx);
        }
        if (!curSys.getCars().isEmpty()){
            curSys.removeFirstCar();
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
