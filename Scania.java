import java.awt.*;

/*
jag böejrade lite
funderar om man kall göra en abstrakt lastbils klass som inheritar CarData
 */

public class Scania extends Car implements Load_Platform{

    public Color color;
    int loadPlatformDegree = 0;

    public static class ScaniaData extends CarData{
        public ScaniaData() {
            m_enginePower = 250;
            m_nrDoors = 2;
            m_modelName = "Scania S-serien";

        }
    }
    public static ScaniaData g_instance = new ScaniaData();

    public Scania(){
        m_carData = g_instance;
        color = Color.GRAY;
        stopEngine();
    }


    @Override
    public void down() {
        if (m_currentSpeed == 0 && loadPlatformDegree == 70) ; {
            loadPlatformDegree += 10;
        }

    }

    @Override
    public void upp() {
        if (m_currentSpeed == 0 && loadPlatformDegree != 70) ; {
            loadPlatformDegree -= 10;
        }

    }

    @Override
    public double speedFactor() {
        if (loadPlatformDegree == 0) {return (m_carData.getEnginePower()/10) * 0.01;
        } else { return 0;}

    }

    @Override
    public void incrementSpeed(double amount) {
        amount = Math.max(0,amount);
        m_currentSpeed = getCurrentSpeed() + speedFactor() * amount;
    }

    @Override
    public void decrementSpeed(double amount) {
        amount = Math.max(0,amount);
        m_currentSpeed = getCurrentSpeed() - speedFactor() * amount;
    }
}
