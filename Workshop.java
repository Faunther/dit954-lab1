import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Workshop {
    private int m_carCapacity = 0;
    private Map<Integer, Car> m_garage = new HashMap<>();

    // if "storage" is kept here.
    // then we cannot replace it with a map for the "multi-brand" workshop

    public Workshop() {
        this(0);

    }
    public Workshop(int carCapacity) {

        this.m_carCapacity = carCapacity;
    }


    public int getCarCapacity() {
        return m_carCapacity;
    }


    // let subclasses override this?
    public int getNumOfCars() {
        return m_garage.size();
    }

}

// members (vars) in interfaces are by default final and static
// as they can't(?) be instantiated on their own.

public class BrandWorkshop<TBrand extends Car> extends Workshop {
    TBrand retrieveCar() {
        // egentligen från en stack/lista
        Car car = new Volvo240();


        return (TBrand)car;
    }
    boolean acceptCar(TBrand car) {
        return true;
    }
}

// Is "independent" the most suitable name?
public class IndependentWorkshop extends Workshop {
    Car retrieveCar() {
        return null;
    }
    boolean acceptCar(Car car) {
        return true;
    }
}


class CID {}
class CIDT<T> extends CID {}

public class MulBraWorkshop extends Workshop {
    public <T>
    void registerBrand() {
        CID cid = new CIDT<T>();
        cid.getClass();

        int hash = new T().getClass().hashCode();
    }

    public <T>
    boolean managesBrand() {
        return false;
    }

    public <T>
    T retrieveCar() {
        return null;
    }
    public <T>
    boolean acceptCar(T car) {
        return true;
    }
}



/*
class CID {
    public <T>
    boolean isType(T arg) { return false; }
}
class CIDT<T> extends CID {
    public
    boolean isType(T arg) { return true; }
}
* */
