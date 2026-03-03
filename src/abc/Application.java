package src.abc;



public class Application {
    public static void main(String[] args) {
        Publisher pub = new Publisher();

        CarSystemFactory carSysFactory = new CarSystemFactory();
        CarSystem<?> saab95Sys = carSysFactory.createSystem("Saab95");
        CarSystem<?> scaniaSys = carSysFactory.createSystem("Scania");
        CarSystem<?> volvo240Sys = carSysFactory.createSystem("Volvo240");

        //Saab95System<Saab95> saabSys = new Saab95System<>();
        //pub.addSubscriber(saabSys);
        //saabSys.addCar(new Saab95());
        //saabSys.addCar(new Saab95());
        //saabSys.addCar(new Saab95());

        //ScaniaSystem<Scania> scaniaSystem = new ScaniaSystem<>();
        //pub.addSubscriber(scaniaSystem);
        //scaniaSystem.addCar(new Scania());
        //scaniaSystem.addCar(new Scania());
        //scaniaSystem.addCar(new Scania());

        Model carModel = new Model();
        carModel.addSystem(saab95Sys);
        carModel.addSystem(scaniaSys);
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
