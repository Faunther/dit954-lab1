import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.Color;
public class BrandWorkshopTest {

    @Test
    public void DefultTest(){
        BrandWorkshop<Car> repshop = new BrandWorkshop<>();
        assertEquals(repshop.getCarCapacity() ,0);

    }
    @Test
    public void NormalTest(){
        BrandWorkshop<Car> repshop = new BrandWorkshop<>(5);
        assertEquals(repshop.getCarCapacity() ,5);
    }
    @Test
    public void WorkshopTest(){
        BrandWorkshop<Car> repshop = new BrandWorkshop<>(5);
        Volvo240 car1 = new Volvo240();
        int id1 = repshop.acceptCar(car1);
        assertEquals(repshop.getNumOfCars() ,1);
        assertEquals(car1, repshop.retrieveCar(id1));





    }



}
