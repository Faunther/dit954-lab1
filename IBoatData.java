public interface IBoatData extends IVehicleData{

    public int getEnginePower();
    public int getMaxSpeed();
    public double getCurrentSpeed();
    public void increaseSpeed();
    public void decreaseSpeed();
    public void startEngine();
    public void stopEngine();
    public boolean isEngineOn();

}
