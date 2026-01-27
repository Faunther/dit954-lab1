import java.awt.*;

// ?! Only useful if this data is never going to change from (car) model to model
abstract class CarData_ {
    protected double m_enginePower; // Engine power of the car
    protected int    m_nrDoors;     // Number of doors on the car
    protected String m_modelName;   // The car model name

    public int    getNrDoors() { return m_nrDoors; };
    public double getEnginePower() { return m_enginePower; }
    public String getModelName() { return m_modelName; }
}


class Car_ {
    protected CarData   m_carData;      // General car data that should be UNIQUE and UNMUTABLE for a model.
    protected Color     m_color;        // Color of the car
    protected double    m_currentSpeed; // The current speed of the car

    // Get-/Setters ===========================================================

    public int getNrDoors(){
        return m_carData.getNrDoors();
    }
    public double getEnginePower() {
        return m_carData.m_enginePower;
    }

    public double getCurrentSpeed() {
        return m_currentSpeed;
    }
    public Color getColor() {
        return m_color;
    }
    public void setColor(Color clr){
        m_color = clr;
    }

    // Methods ===========================================================
    public void startEngine() {
        m_currentSpeed = 0.1;
    }
    public void stopEngine() {
        m_currentSpeed = 0;
    }
    public void gas(double amount) {
        incrementSpeed(amount);
    }
    public void brake(double amount) {
        decrementSpeed(amount);
    }

    // overload
    public double speedFactor() { return m_carData.getEnginePower() * 0.01; }
    public void incrementSpeed(double amount) { m_currentSpeed += amount;}
    public void decrementSpeed(double amount) { m_currentSpeed -= amount; }
}

/*class Volvo240_ extends Car {
    private static class Volvo240Data extends CarData {
        Volvo240Data() {
            m_enginePower = 100;
            m_nrDoors = 4;
            m_modelName = "Volvo240";
        }
    }
    private static final Volvo240Data g_instance = new Volvo240Data();

    // ?! Inte private?
    public final static double trimFactor = 1.25;

    Volvo240_() {
        m_carData = g_instance;
        m_color = Color.black;
        stopEngine();
    }
    Volvo240_(Color aColor) {
        m_carData = g_instance;
        m_color = aColor;
        stopEngine();
    }

}
*/

public class Similarities {
    // variables =============================================================
    // (r) / final? Unique?
    public double enginePower; // Engine power of the car
    public int nrDoors; // Number of doors on the car
    public String modelName; // The car model name

    // (rw) / mutable
    public double currentSpeed; // The current speed of the car
    public Color color; // Color of the car

    // methods =============================================================

    public int getNrDoors(){
        return nrDoors;
    }
    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){ return currentSpeed; }
    public Color getColor(){ return color; }
    public void setColor(Color clr){ color = clr; }
    public void startEngine(){ currentSpeed = 0.1; }
    public void stopEngine(){
        currentSpeed = 0;
    }

    public void gas(double amount){ incrementSpeed(amount); }
    public void brake(double amount){ decrementSpeed(amount); }

    // overload
    public double speedFactor(){ return 0.0; }
    public void incrementSpeed(double amount){ currentSpeed = getCurrentSpeed() + speedFactor() * amount;}
    public void decrementSpeed(double amount){ currentSpeed = getCurrentSpeed() - speedFactor() * amount; }
}
