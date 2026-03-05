package src.abc;

import src.CarBrandWorkshop;
import src.abc.utils.Pair;
import src.vehicles.Volvo240;

import java.awt.*;
import java.util.ArrayList;

public class Volvo240System<T extends Volvo240> extends CarSystem<T> {
    @Override
    public void onMoveTickEvent() {
        // double volvoWorkshopPoint = frame.drawPanel.volvoWorkshopPoint;
        // double workshopSize = frame.drawPanel.getWorkshopSize();
        Dimension workshopSize = new Dimension(10, 10);

        ArrayList<T> carsToRemove = new ArrayList<>();

        for (T car : m_cars) {
            updateCar(car);

            // turn this into function ?
            var cp = car.getPoint();

            // Dimension dim = frame.drawPanel.getCarSize(car);
            Dimension dim = new Dimension(10, 10);

            for (Pair<Point, CarBrandWorkshop<T>> placedWorkshop : m_workshops) {
                Point pos = placedWorkshop.getFirst();
                CarBrandWorkshop<T> workshop = placedWorkshop.getSecond();

                Rectangle cr = new Rectangle(
                        new Point(
                                (int) Math.round(cp.getX()),
                                (int) Math.round(cp.getY())),
                        dim);
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
