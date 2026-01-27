import java.awt.*;

public abstract class Car {
    protected CarData   m_carData;      // General car data that should be UNIQUE and UNMUTABLE for a model.
    protected Color m_color;        // Color of the car
    protected double    m_currentSpeed;// The current speed of the car
    //implements


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
    public abstract double speedFactor();
    public abstract void incrementSpeed(double amount);
    public abstract void decrementSpeed(double amount);
}
