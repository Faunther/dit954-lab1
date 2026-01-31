import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Car implements Movable {
    protected CarData   m_carData;      // General car data that should be UNIQUE and UNMUTABLE for a model.
    protected Color m_color;        // Color of the car
    protected double    m_currentSpeed = 0;// The current speed of the car

    protected Point2D.Double m_position = new Point2D.Double(0, 0);
    protected double m_direction = Math.toRadians(90); // Startar med positiv riktning y

    //implements
    public void move() {
        m_position.y += m_currentSpeed * Math.round(Math.sin(m_direction));
        m_position.x += m_currentSpeed * Math.round(Math.cos(m_direction));
    }
    public void turnLeft() {
        m_direction = m_direction - Math.toRadians(90);
    }
    public void turnRight() {
        m_direction = m_direction + Math.toRadians(90);
    }

    public void printPoint() {
        System.out.println(m_position);
    }
    public Point2D.Double getPoint(){
        return m_position;
    }
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

    //
    public void gas(double amount) {
        amount = Math.clamp(amount,0,1);
        incrementSpeed(amount);
    }
    public void brake(double amount) {
        amount = Math.clamp(amount,0,1);
        decrementSpeed(amount);
    }
    // overload
    public abstract double speedFactor();
    public abstract void incrementSpeed(double amount);
    public abstract void decrementSpeed(double amount);
}
