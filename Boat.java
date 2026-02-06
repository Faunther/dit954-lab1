import java.awt.geom.Point2D;
import java.util.Stack;

public abstract class Boat implements BoatData, IMovable {
    Point2D.Double m_position;
    private double m_direction;
    private double m_currentSpeed;
    private boolean m_EngenOn;


    public Boat(){
        m_position = new Point2D.Double(0, 0);
        m_direction = Math.toRadians(90);
        m_currentSpeed = 0;
        m_EngenOn = false;

    }

    @Override
    public double getCurentSpeed() {
        return m_currentSpeed;
    }

    @Override
    public void incresSpeed() {
        m_currentSpeed = Math.min(getMaxSpeed(),m_currentSpeed + this.getEngenPower() * 0.01);
    }

    @Override
    public void decresSpeed() {
        m_currentSpeed = Math.max(0,m_currentSpeed + this.getEngenPower() * 0.01);
    }

    @Override
    public void startEngen() {
        m_EngenOn = true;
    }

    @Override
    public void stopEngen() {
        m_EngenOn = false;
    }

    @Override
    public boolean getEngen() {
        return m_EngenOn;
    }


    @Override
    public void move() {
        m_position.y += m_currentSpeed * Math.round(Math.sin(m_direction));
        m_position.x += m_currentSpeed * Math.round(Math.cos(m_direction));

    }

    @Override
    public void turnLeft() {
        m_direction = m_direction - Math.toRadians(90);
    }

    @Override
    public void turnRight() {
        m_direction = m_direction + Math.toRadians(90);
    }

}
