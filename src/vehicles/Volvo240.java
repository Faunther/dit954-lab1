package src.vehicles;

import java.awt.*;

public class Volvo240 extends Car {

    public final double trimFactor;
    public Color color; // Color of the car

    // "CarData" Implement ====================================================
    @Override
    public int getNrDoors() { return 2; }
    @Override
    public double getEnginePower() { return 125; }
    @Override
    public String getModelName() { return "Saab95"; }

    public Volvo240() {
        color = Color.black;
        trimFactor = 1.25;
        stopEngine();
    }
    public Volvo240(int x, int y) {
        color = Color.black;
        trimFactor = 1.25;
        stopEngine();
        m_position.x = x;
        m_position.y = y;
    }

    @Override
    public double speedFactor() {
        return this.getEnginePower() * 0.01 * trimFactor;
    }
}
