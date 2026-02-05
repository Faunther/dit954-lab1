public interface BoatData extends IVehicleData{

    public int getEngenPower();
    public int getMaxSpeed();
    public double getCurentSpeed();
    public void incresSpeed();
    public void decresSpeed();
    public void startEngen();
    public void stopEngen();
    public boolean getEngen();

}
