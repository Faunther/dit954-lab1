package vehicles;

import vehicles.loadable.IRamp;

public class Scania extends Car implements IRamp {
    private double m_bedAngle = 0.0;

    // "CarData" Implement ====================================================
    @Override
    public int getNrDoors() { return 2; }
    @Override
    public double getEnginePower() { return 700; }
    @Override
    public String getModelName() { return "Scania"; }


    public Scania() {
        stopEngine();
    }


    // Get-/Setters ===========================================================

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
        return this.getEnginePower() * 0.001;
    }
}
