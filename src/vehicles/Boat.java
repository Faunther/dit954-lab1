package src.vehicles;

import java.awt.geom.Point2D;

public abstract class Boat implements IBoatData, IMovable {
    Point2D.Double m_position;
    private double m_directionAngle;
    private double m_currentSpeed;
    private boolean m_isEngineOn;

    public Boat() {
        m_position = new Point2D.Double(0, 0);
        m_directionAngle = Math.toRadians(90);
        m_currentSpeed = 0;
        m_isEngineOn = false;
    }

    @Override
    public double getCurrentSpeed() {
        return m_currentSpeed;
    }

    @Override
    public void increaseSpeed() {
        m_currentSpeed = Math.min(getMaxSpeed(), m_currentSpeed + this.getEnginePower() * 0.01);
    }

    @Override
    public void decreaseSpeed() {
        m_currentSpeed = Math.max(0, m_currentSpeed + this.getEnginePower() * 0.01);
    }

    @Override
    public void startEngine() {
        m_isEngineOn = true;
    }

    @Override
    public void stopEngine() {
        m_isEngineOn = false;
    }

    @Override
    public boolean isEngineOn() {
        return m_isEngineOn;
    }

    @Override
    public void move() {
        m_position.y += m_currentSpeed * Math.round(Math.sin(m_directionAngle));
        m_position.x += m_currentSpeed * Math.round(Math.cos(m_directionAngle));

    }

    @Override
    public void turnLeft() {
        m_directionAngle = m_directionAngle - Math.toRadians(90);
    }

    @Override
    public void turnRight() {
        m_directionAngle = m_directionAngle + Math.toRadians(90);
    }

}
