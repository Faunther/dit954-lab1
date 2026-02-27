package src.vehicles;



public class CarFactory {

    public Car createCar(String string){
        if (string.equals("Volvo240")) {
            return new Volvo240();
        }
        if (string.equals("Saab95")) {
            return new Saab95();
        }
        if (string.equals("Scania")) {
            return new Scania();
        }
        return null;
    }


}
