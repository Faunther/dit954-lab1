public interface IRamp {

    void rampDown();
    void rampUpp();


    // Should Ramp really be responsible for this?
    public int numberOfCarsOnRamp();
    public int getMaxCars();
    void loadCarOnToRamp(Car car);



}
