package src.abc;

import src.CarBrandWorkshop;
import src.abc.utils.Pair;
import src.vehicles.Volvo240;

import java.awt.*;
import java.util.ArrayList;

public class Volvo240System<T extends Volvo240> extends CarSystem<T> {
    @Override
    public void onMoveTickEvent() {
        CarConfig cfg = CarConfig.getSingleton();

        ArrayList<T> carsToRemove = new ArrayList<>();

        for (T car : m_cars) {
            updateCar(car);

            var cp = car.getPoint();
            Dimension carDim = cfg.getDimension(car.getModelName());

            for (Pair<Point, CarBrandWorkshop<T>> placedWorkshop : m_workshops) {
                Point pos = placedWorkshop.getFirst();
                CarBrandWorkshop<T> workshop = placedWorkshop.getSecond();

                Dimension workshopSize = cfg.getDimension(workshop.getWorkshopName());

                Rectangle cr = new Rectangle(new Point(cp.x, cp.y), carDim);
                Rectangle wr = new Rectangle(pos, workshopSize);
                if (cr.intersects(wr)) {
                    // Transfer ownership to the workshop (and also stop rendering)
                    workshop.acceptCar(car);
                    carsToRemove.add(car);
                }
            }
        }
        for (T car : carsToRemove) {
            m_cars.remove(car);
        }

    }
}
