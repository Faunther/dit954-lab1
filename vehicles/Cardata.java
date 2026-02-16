package vehicles;

abstract class CarData {
    protected double m_enginePower; // Engine power of the car
    protected int m_nrDoors; // Number of doors on the car
    protected String m_modelName; // The car model name

    public int getNrDoors() {
        return m_nrDoors;
    };

    public double getEnginePower() {
        return m_enginePower;
    }

    public String getModelName() {
        return m_modelName;
    }
}
