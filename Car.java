import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Car implements Movable {
    protected CarData   m_carData;      // General car data that should be UNIQUE and UNMUTABLE for a model.
    protected Color m_color;        // Color of the car
    protected double    m_currentSpeed = 0;// The current speed of the car

    protected Point2D.Double m_positon = new Point2D.Double(0, 0);
    protected double m_directon = Math.toRadians(90); // Startar med positiv riktning y

    //implements
    public void move() {
        m_positon.y += m_currentSpeed*Math.round(Math.sin(m_directon));
        m_positon.x += m_currentSpeed*Math.round(Math.cos(m_directon));
    }
    public void turnLeft() {
        m_directon = m_directon - Math.toRadians(90);
    }
    public void turnRight() {
        m_directon = m_directon + Math.toRadians(90);
    }

    public void print_locaton() {
        System.out.println(m_positon);
    }
    public Point2D.Double getpoint(){
        return m_positon;
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
