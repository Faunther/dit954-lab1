package src.vehicles;

import java.awt.*;

public class Saab95 extends Car {
    private boolean m_TurboOn;

    // "CarData" Implement ====================================================
    @Override
    public int getNrDoors() { return 2; }
    @Override
    public double getEnginePower() { return 125; }
    @Override
    public String getModelName() { return "Saab95"; }

    public Saab95() {
        m_color = Color.red;
        m_TurboOn = false;
        stopEngine();
    }
    public Saab95(int x, int y) {
        m_color = Color.red;
        m_TurboOn = false;
        stopEngine();
        m_position.x = x;
        m_position.y = y;


    }

    public void setTurboOn() {
        m_TurboOn = true;
    }

    public void setTurboOff() {
        m_TurboOn = false;
    }

    public boolean isTurboOn() { return m_TurboOn; }

    @Override
    public double speedFactor() {
        double turbo = 1;
        if (m_TurboOn)
            turbo = 1.3;
        return this.getEnginePower() * 0.01 * turbo;
    }
}
