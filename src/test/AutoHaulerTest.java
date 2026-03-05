package src.test;

import org.junit.Test;

import src.vehicles.AutoHauler;
import src.vehicles.Car;
import src.vehicles.Volvo240;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class AutoHaulerTest {
    @Test
    public void testHauling() {
        AutoHauler ah = new AutoHauler();
        ah.startEngine();

        assertEquals(false, ah.getRampIsDown());

        assertThrows("Can not load cars while ramp is up", Error.class, () -> {
            Volvo240 c = new Volvo240();
            ah.loadCar(c);
        });
        ah.gas(2);
        assertThrows("Can not lower ramp while speed!=0", Error.class, () -> {
            ah.rampDown();
        });
        ah.brake(2);

        assertEquals(false, ah.getRampIsDown());
        ah.rampDown();

        Volvo240 c1 = new Volvo240();
        ah.loadCar(c1);
        assertEquals(c1, ah.unloadCar());

        List<Car> cars = new ArrayList<Car>();
        for (int i = 0; i < 5; i++) {
            Volvo240 ci = new Volvo240();
            ah.loadCar(ci);
            cars.add(ci);
        }
        assertThrows("Limit maximum number of loaded cars", Error.class, () -> {
            Volvo240 c2 = new Volvo240();
            ah.loadCar(c2);
        });

        assertThrows("Should not be able to move while ramp is down", Error.class, () -> {
            ah.gas(2);
        });

        ah.rampUp();
        ah.brake(2);

        ah.move();
        ah.turnLeft();
        ah.move();
        for (Car car : cars) {
            assertEquals(ah.getPoint(), car.getPoint());
            assertEquals(ah.getDirection(), car.getDirection(), 0.0001);
        }
    }

    @Test
    public void testLoadCarSize() {
        AutoHauler ah = new AutoHauler();
        assertThrows("Should not be able to load another auto hauler", Error.class, () -> {
            AutoHauler h2 = new AutoHauler();
            ah.loadCar(h2);
        });
    }
}
