public class Scania extends Car implements IRamp {
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
    public void rampDown() {
        if (this.m_currentSpeed != 0)
            throw new Error("can not move ramp while moving");
        m_bedAngle = 70;
    }

    @Override
    public void rampUp() {
        m_bedAngle = 0;
    }

    @Override
    public boolean getRampIsDown() {
        return this.m_bedAngle >= 0.1;
    }

    @Override
    public double speedFactor() {
        if (getRampIsDown())
            throw new Error("can not move while bed is down");

        // TODO: Should this be dependant on the load or something?
        return m_carData.getEnginePower() * 0.001;
    }

    @Override
    public void incrementSpeed(double amount) {
        if (getRampIsDown())
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
