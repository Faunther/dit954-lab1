package vehicles;

import java.awt.*;

public class Saab95 extends Car {

    public boolean turboOn;
    public Color color; // Color of the car

    public static class Saab95Data extends CarData {
        public Saab95Data() {
            m_nrDoors = 2;
            m_enginePower = 125;
            m_modelName = "Saab95";
        }
    }

    // TODO: Should this be public?
    private static Saab95Data g_instancr = new Saab95Data();

    public Saab95() {
        m_carData = g_instancr;
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
        return m_carData.getEnginePower() * 0.01 * turbo;
    }
}
