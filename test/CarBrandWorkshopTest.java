import org.junit.Test;

import static org.junit.Assert.*;

public class CarBrandWorkshopTest {
    @Test
    public void DefultTest(){
        CarBrandWorkshop<Car> repshop = new CarBrandWorkshop<>();
        assertEquals(repshop.getCarCapacity() ,0);

    }

    @Test
    public void NormalTest(){
        CarBrandWorkshop<Car> repshop = new CarBrandWorkshop<>(5);
        assertEquals(repshop.getCarCapacity() ,5);
    }
    @Test
    public void WorkshopTest(){
        CarBrandWorkshop<Car> repshop = new CarBrandWorkshop<>(5);
        Volvo240 car1 = new Volvo240();
        int id1 = repshop.acceptCar(car1);
        assertEquals(repshop.getNumOfCars() ,1);
        assertEquals(car1, repshop.retrieveCar(id1));
    }

    // Suggestions
    /*
    * Try to hand in more cars than there's capacity for.
    * Try to retrieve a car that is in another workshop
    * Try to retrieve a car using an invalid "ticket" (car id)
    *
    * Don't know if we can attempt "static-error" tests
    * */

}
