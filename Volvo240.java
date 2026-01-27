import java.awt.*;

public class Volvo240 extends Car{

    public final double trimFactor;
    public Color color; // Color of the car

    public static class Volvo240Data extends CarData{
        public Volvo240Data() {
            m_enginePower = 100;
            m_nrDoors = 4;
            m_modelName = "Volvo240";
        }
    }

    public static Volvo240Data g_instancr = new Volvo240Data();
    
    public Volvo240(){
        m_carData = g_instancr;
        color = Color.black;
        trimFactor = 1.25;
        stopEngine();
    }

    @Override
    public double speedFactor(){
        return m_carData.getEnginePower() * 0.01 * trimFactor;
    }
    @Override
    public void incrementSpeed(double amount){
	    m_currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,m_carData.getEnginePower());
    }
    @Override
    public void decrementSpeed(double amount){
        m_currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }
}
