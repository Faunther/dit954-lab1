import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class CarBrandWorkshopTest {
    @Test
    public void defultTest(){
        CarBrandWorkshop<Car> repshop = new CarBrandWorkshop<>();
        assertEquals(repshop.getCarCapacity() ,0);

    }

    @Test
    public void normalTest(){
        CarBrandWorkshop<Car> repshop = new CarBrandWorkshop<>(5);
        assertEquals(repshop.getCarCapacity() ,5);
    }
    @Test
    public void workshopTest(){
        CarBrandWorkshop<Car> repshop = new CarBrandWorkshop<>(5);
        Volvo240 car1 = new Volvo240();
        int id1 = repshop.acceptCar(car1);
        assertEquals(repshop.getNumOfCars() ,1);
        assertEquals(car1, repshop.retrieveCar(id1));
    }
    @Test
    public void capacityTest(){
        CarBrandWorkshop<Car> repshop = new CarBrandWorkshop<>(2);
        Volvo240 car1 = new Volvo240();
        int id1 = repshop.acceptCar(car1);
        Volvo240 car2 = new Volvo240();
        int id3 = repshop.acceptCar(car2);
        Volvo240 car3 = new Volvo240();
        assertEquals(null,repshop.acceptCar(car3));
    }
    @Test
    public void retrieveCarTest(){
        CarBrandWorkshop<Car> repshop1 = new CarBrandWorkshop<>(2);
        Volvo240 car1 = new Volvo240();
        int id1 = repshop1.acceptCar(car1);

        CarBrandWorkshop<Car> repshop2 = new CarBrandWorkshop<>(2);
        Saab95 car2 = new Saab95();
        int id2 = repshop2.acceptCar(car2);

        assertEquals(null, repshop2.retrieveCar(id1));
        assertEquals(null, repshop1.retrieveCar(id2));
    }





    // Suggestions
    /*
    *
    * Don't know if we can attempt "static-error" tests
    * */

}
