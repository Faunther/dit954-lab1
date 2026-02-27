package src.abc;

import src.vehicles.Scania;

public class ScaniaSystem<T extends Scania> extends CarSystem<T> implements IRaiseLowerBedSubscriber {
    public void onRaiseBed() {
        for (Scania s : m_cars) {
            s.rampUp();
        }
    }

    public void onLowerBed() {
        for (Scania s : m_cars) {
            s.rampDown();
        }
    }
}
