package src.abc;

import java.util.ArrayList;

import src.vehicles.Car;
import src.vehicles.Saab95;
import src.vehicles.Scania;

public class Application {
    public static void main(String[] args) {
        Publisher pub = new Publisher();

        Saab95System<Saab95> saabSys = new Saab95System<>();
        pub.addSubscriber(saabSys);
        saabSys.addCar(new Saab95());
        saabSys.addCar(new Saab95());
        saabSys.addCar(new Saab95());

        ScaniaSystem<Scania> scaniaSystem = new ScaniaSystem<>();
        pub.addSubscriber(scaniaSystem);
        scaniaSystem.addCar(new Scania());
        scaniaSystem.addCar(new Scania());
        scaniaSystem.addCar(new Scania());

        Model carModel = new Model();
        carModel.addSystem(saabSys);
        carModel.addSystem(scaniaSystem);
        // carModel.addCarBrandWorkshop(); // handle here?

        /*
        ArrayList<CarSystem<?>> systems = new ArrayList<>();
        systems.add(saabSys);
        systems.add(scaniaSystem);

        ArrayList<Car> outCars = new ArrayList<>();
        for (CarSystem<?> carSystem : systems) {
            var cars = carSystem.getCars();
            for (Car car : outCars) {
                String model = car.getModelName();
                render(model, car);
            }
        }
        * */

        // Used to store as IView
        CarView v = new CarView(carModel);
        v.addModelImage("Saab95", "images/saab95.png");
        v.addSubscriber(pub); // bind IActionEventHandler to view
        pub.addSubscriber(v); // bind IGameTickSubscriber to Publisher
    }

}
