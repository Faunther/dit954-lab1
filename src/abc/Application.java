package src.abc;



public class Application {
    public static void main(String[] args) {
        Publisher pub = new Publisher();

        CarSystemFactory carSysFactory = new CarSystemFactory();
        CarSystem<?> saab95Sys = carSysFactory.createSystem("Saab95");
        CarSystem<?> scaniaSys = carSysFactory.createSystem("Scania");
        CarSystem<?> volvo240Sys = carSysFactory.createSystem("Volvo240");

        Model carModel = new Model();
        carModel.addSystem(saab95Sys);
        carModel.addSystem(scaniaSys);
        // carModel.addCarBrandWorkshop(); // handle here?

        // Used to store as IView
        CarView v = new CarView(carModel);
        v.addModelImage("Saab95", "images/saab95.png");
        v.addSubscriber(pub); // bind IActionEventHandler to view
        pub.addSubscriber(v); // bind IGameTickSubscriber to Publisher
    }

}
