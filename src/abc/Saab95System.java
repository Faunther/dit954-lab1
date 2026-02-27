package src.abc;

import src.vehicles.Saab95;

public class Saab95System<T extends Saab95> extends CarSystem<T> implements ITurboSubscriber {
    public void onTurboEvent(boolean isOn) {
        for (Saab95 s : m_cars) {
            if (isOn)
                s.setTurboOn();
            else
                s.setTurboOff();
        }
    }
}
