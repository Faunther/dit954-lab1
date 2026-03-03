package src.abc;

import src.CarBrandWorkshop;
import src.vehicles.Saab95;
import src.vehicles.Scania;
import src.vehicles.Volvo240;
import java.util.Objects;

public class CarSystemFactory implements ICarSystemFactory {
    final private ICarConstructor<Scania> m_scaniaBuilder;
    final private ICarConstructor<Saab95> m_saab95Builder;
    final private ICarConstructor<Volvo240> m_volvo240Builder;

    public CarSystemFactory() {
        m_scaniaBuilder = new ICarConstructor<>() {
            @Override
            public Scania makeCar(int x, int y) { return new Scania(x, y); }
            @Override
            public CarBrandWorkshop<Scania> makeWorkshop(int capacity) {
                return new CarBrandWorkshop<>(capacity);
            }
        };
        m_saab95Builder = new ICarConstructor<>() {
            @Override
            public Saab95 makeCar(int x, int y) {
                return new Saab95(x, y);
            }
            @Override
            public CarBrandWorkshop<Saab95> makeWorkshop(int capacity) {
                return new CarBrandWorkshop<>(capacity);
            }
        };
        m_volvo240Builder = new ICarConstructor<>() {
            @Override
            public Volvo240 makeCar(int x, int y) {
                return new Volvo240(x,y);
            }
            @Override
            public CarBrandWorkshop<Volvo240> makeWorkshop(int capacity) {
                return new CarBrandWorkshop<>(capacity);
            }
        };

    }

    public CarSystem<?> createSystem(String type) throws IllegalArgumentException {
        if (Objects.equals(type, "Scania")) {
            ScaniaSystem<Scania> scaniaSys = new ScaniaSystem<>(m_scaniaBuilder);
            // do extra stuff?
            // ? IViewActionsHandler::addSubscriber(scaniaSys)
            return scaniaSys;
        }
        if (Objects.equals(type, "Saab95")) {
            Saab95System<Saab95> saabSys = new Saab95System<>(m_saab95Builder);
            // do extra stuff?
            return saabSys;
        }
        if (Objects.equals(type, "Volvo240")) {
            Volvo240System<Volvo240> volvoSys = new Volvo240System<>(m_volvo240Builder);
            // do extra stuff?
            return volvoSys;
        }

        throw new IllegalArgumentException("No matching CarSystem type: " + type);
    }
}
