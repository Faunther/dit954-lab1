public class Scania extends Car {
    private double m_bedAngle = 0.0;

    public static class ScaniaData extends CarData {
        public ScaniaData() {
            m_nrDoors = 2;
            m_enginePower = 700;
            m_modelName = "Scania";
        }
    }

    private static ScaniaData g_instance = new ScaniaData();

    public Scania() {
        m_carData = g_instance;
        stopEngine();
    }

    public double getCurrentBedAngle() {
        return m_bedAngle;
    }

    public void setBedAngle(double angle) {
        if (this.m_currentSpeed != 0 && angle != 0)
            throw new Error("can not move bed while moving");

        m_bedAngle = Math.clamp(angle, 0, 70);
    }

    @Override
    public double speedFactor() {
        // TODO: Should this be dependant on the load or something?
        return m_carData.getEnginePower() * 0.001;
    }

    @Override
    public void incrementSpeed(double amount) {
        if (this.m_bedAngle != 0)
            throw new Error("Can not change speed while bed is lowered");

        // TODO: Trucks can only go like 80 kph?
        amount = Math.max(0, amount);
        m_currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, m_carData.getEnginePower());
    }

    @Override
    public void decrementSpeed(double amount) {
        amount = Math.max(0, amount);
        m_currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

}
