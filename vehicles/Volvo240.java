package vehicles;

import java.awt.*;

public class Volvo240 extends Car {

    public final double trimFactor;
    public Color color; // Color of the car

    public static class Volvo240Data extends CarData {
        public Volvo240Data() {
            m_enginePower = 100;
            m_nrDoors = 4;
            m_modelName = "Volvo240";
        }
    }

    public static Volvo240Data g_instance = new Volvo240Data();

    public Volvo240() {
        m_carData = g_instance;
        color = Color.black;
        trimFactor = 1.25;
        stopEngine();
    }

    @Override
    public double speedFactor() {
        return m_carData.getEnginePower() * 0.01 * trimFactor;
    }
}
