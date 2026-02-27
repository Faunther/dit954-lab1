package src.abc;

import java.util.ArrayList;

import src.CarView;
import src.IView;
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



        IView v = new CarView();
        v.addModelImage("Saab95", "images/saab95.png");
        v.addSubscriber(pub);
    }

}
