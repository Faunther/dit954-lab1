import java.util.ArrayList;

import vehicles.Car;
import vehicles.Saab95;

public interface ISystemSubscriber {
    subscribe(ISystemSubscriber cc);
}

public class CarSystem<T extends Car> implements ISystemSubscriber {
    protected ArrayList<T> m_cars = new ArrayList<>();

    public subscribe(ISystemEventPulisher cc) {
        cc.subscribeMoveTick(this.moveTick);
    }

    private moveTick(){
        for (Car car : m_cars) {
            car.move();
        }
    }
}

public class SaabSystem<T extends Saab95> extends CarSystem<T> {

    public subscribe(ISystemEventPulisher cc) {
        super();
        cc.subscribeMoveTick(this.moveTick);
    }

    private turboOn() {
        for (Saab95 s : m_cars) {
            s.setTurboOn();
        }
    }
}