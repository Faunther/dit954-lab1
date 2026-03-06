package src.vehicles;

import java.awt.*;

public abstract class Car implements IMovable {
    protected Color m_color; // Color of the car
    protected double m_currentSpeed = 0;// The current speed of the car

    protected Point m_position = new Point(0, 0);
    protected double m_direction = Math.toRadians(0); // Startar med positiv riktning y

    // implements
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

    // "CarData" Getters ======================================================
    abstract public int getNrDoors();

    abstract public double getEnginePower();

    abstract public String getModelName();

    // Get-/Setters ===========================================================
    public Point getPoint() {
        return m_position;
    }

    public double getDirection() {
        return m_direction;
    }

    public double getCurrentSpeed() {
        return m_currentSpeed;
    }

    public Color getColor() {
        return m_color;
    }

    public void setColor(Color clr) {
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
        if (m_currentSpeed == 0.0)
            return;
        amount = Math.clamp(amount, 0, 1);
        incrementSpeed(amount);
    }

    public void brake(double amount) {
        amount = Math.clamp(amount, 0, 1);
        decrementSpeed(amount);
    }

    private void incrementSpeed(double amount) {
        m_currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, this.getEnginePower());
    }

    private void decrementSpeed(double amount) {
        m_currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    // overload
    public abstract double speedFactor();
}
