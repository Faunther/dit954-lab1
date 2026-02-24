package vehicles;

import java.awt.*;

public class Saab95 extends Car {

    public boolean turboOn;
    public Color color; // Color of the car

    // "CarData" Implement ====================================================
    @Override
    public int getNrDoors() { return 2; }
    @Override
    public double getEnginePower() { return 125; }
    @Override
    public String getModelName() { return "Saab95"; }

    public Saab95() {
        color = Color.red;
        turboOn = false;
        stopEngine();
    }

    public void setTurboOn() {
        turboOn = true;
    }

    public void setTurboOff() {
        turboOn = false;
    }

    @Override
    public double speedFactor() {
        double turbo = 1;
        if (turboOn)
            turbo = 1.3;
        return this.getEnginePower() * 0.01 * turbo;
    }
}
