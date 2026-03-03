package src.abc;

import src.vehicles.Saab95;

public class Saab95System<T extends Saab95> extends CarSystem<T> implements ITurboSubscriber {
    public Saab95System(ICarConstructor<T> builder) {
        super(builder);
    }

    public void onTurboEvent(boolean isOn) {
        for (Saab95 s : m_cars) {
            if (isOn)
                s.setTurboOn();
            else
                s.setTurboOff();
        }
    }
}
