<!-- https://mermaid.js.org/syntax/classDiagram.html -->

# UML Digram - before refactoring

```
+   Public
-   Private
#   Protected
~   Package/Internal
```

```
<|--    Inheritance
*--     Composition (A owns B, B can't be independent)
o--	    Aggregation (A owns B, B can be independent
-->     Association
--      Link (Solid)
..>     Dependency
..|>    Realization
..      Link (Dashed)
```

```mermaid
classDiagram

    Car <|-- Scania: extends
    Car <|-- Volvo240: extends
    Car <|-- Saab95: extends
    Car <|-- AutoHauler: extends
    CarData <|-- CarHaulerData
    CarData <.. Scania
    CarData <.. Volvo240
    CarData <.. Saab95
    CarHaulerData <.. AutoHauler

    class CarHaulerData {
        #int m_maxCarsLoaded
        getMaxCarsLoaded(int)
    }

    class CarData {
        #double m_enginePower
        #int m_nrDoors
        #String m_modelName
        +getNrDoors() int
        +getEnginePower() double
        +getModelName() String
    }

    class IMovable {
        ~ move()*
        ~ turnLeft()*
        ~ turnRight()*
    }

    class IRamp {
        ~ rampDown();
        ~ rampUp();
        ~ getRampIsDown(boolean);
    }

    <<interface>> IMovable
    IMovable <|.. Car: implements
    IMovable <|.. Saab95: implements
    IMovable <|.. Scania: implements
    IMovable <|.. Volvo240: implements
    IMovable <|.. AutoHauler: implements
    CarData *-- Car
    <<interface>> IRamp
    IRamp <|.. Scania: implements
    IRamp <|.. AutoHauler: implements

    class Car {
        #CarData m_carData
        #Color m_color
        #double m_currentSpeed
        #Point2D.Double m_position
        #double m_direction
        +move()
        +turnLeft()
        +turnRight()
        +getPoint() Point2D.Double
        +getDirection() double
        +getNrDoors() int
        +getModelName() String
        +getEnginePower() double
        +getCurrentSpeed() double
        +getColor() Color
        +setColor(Color newColor)
        +startEngine()
        +stopEngine()
        +gas()
        +brake()
        #speedFactor()* double
    }

    class Scania {
        #int m_nrDoors
        #double m_enginePower
        #String m_modelName
        -double m_bedAngle
        +getCurrentBedAngle()
        +rampDown()
        +rampUp()
        +getRampIsDown(boolean)
        +speedFactor(double)
    }
    class Volvo240 {
        #int m_nrDoors
        #double m_enginePower
        #String m_modelName
        +Color color
        +double trimFactor
        +speedFactor(double)
    }
    class Saab95 {
        #int m_nrDoors
        #double m_enginePower
        #String m_modelName
        +boolean turbo
        +Color color
        +setTurboOn()
        +setTurboOff()
        +speedFactor(double)
    }
    class AutoHauler {
        #int m_nrDoors
        #double m_enginePower
        #String m_modelName
        #int m_maxCarsLoaded
        -boolean m_rampIsDown
        -Stack<> m_loadedCars
        +getRampIsDown()
        +rampDown()
        +rampUp()
        +loadCar(Car)
        +unloadCar(Car)
        +updateLoadedCarsPosition()
        +move()
        +turnLeft()
        +turnRight()
        +speedFactor(double)
    }

    Car <|-- src.CarBrandWorkshop: TBrand extends Car

    class src.CarBrandWorkshop {
        -int m_carCapacity
        #Map<Integer, TBrand> m_garage
        +getCarCapacity(int)
        +getNumOfCars(int)
        +canAcceptCar(boolean)
        ~ acceptCar(Integer)
        ~ retrieveCar(TBrand)
    }

%% Swing library
namespace Swing {
    class JPanel
    class JFrame
    class JButton
    class JLabel
    class JSpinner

    class Graphics
    class FlowLayout
    class Image
    class BufferedImage
    class ActionListener
    class ActionEvent
    class ChangeListener

    class SpinnerNumberModel
    class BorderLayout
    class Toolkit
}

    class JPanel { }
    class JFrame { }
    class JButton { }
    class Graphics { }
    class FlowLayout { }
    class Image { }
    class BufferedImage { }
    class ActionListener { }
    <<interface>> ActionListener
    class ActionEvent { }

    class ChangeListener { }
    <<interface>> ChangeListener

    class SpinnerNumberModel { }
    class BorderLayout { }
    class Timer { }
    class Toolkit { }

    class src.DrawPanel {
        ~ BufferedImage volvoWorkshopImage
        ~ Point volvoWorkshopPoint
        ~ ArrayList<Car> cars
        ~ HashMap<String, Image> carImages
        
        + src.DrawPanel(int, int)
        + getCarWidth(Car) int
        + getCarHeight(Car) int
        + getCarSize(Car) Dimension
        + getWorkshopSize() Dimension
        # paintComponent(Graphics)
        ~ setCars(ArrayList<Car>) void
    }
    JPanel <|-- src.DrawPanel: extends
    src.DrawPanel --* Image
    src.DrawPanel --* BufferedImage
    src.DrawPanel --o Car
    src.DrawPanel --> Graphics

    class src.CarViewOld {
        -int X$
        -int Y$
        ~ int gasAmount
        ~ src.CarController carC
        ~ src.DrawPanel drawPanel
        ~ JSpinner gasSpinner
        ~ JLabel gasLabel
        ~ JPanel controlPanel
        ~ JPanel gasPanel
        ~ JButton gasButton
        ~ JButton brakeButton
        ~ JButton turboOnButton
        ~ JButton turboOffButton
        ~ JButton liftBedButton
        ~ JButton lowerBedButton
        ~ JButton startButton
        ~ JButton stopButton
        
        + src.CarViewOld(String, src.CarController)
        - initComponents(String) void
    }
    JFrame <|-- src.CarViewOld
    src.CarViewOld --o src.CarController
    src.CarViewOld ..o src.DrawPanel
    src.CarViewOld --* JSpinner
    src.CarViewOld --* JLabel
    src.CarViewOld --* JPanel
    src.CarViewOld --* JButton
    src.CarViewOld --> FlowLayout
    src.CarViewOld --> BorderLayout
    src.CarViewOld --> Toolkit
    src.CarViewOld --> SpinnerNumberModel
    src.CarViewOld --> ActionEvent
    src.CarViewOld --> ActionListener
    src.CarViewOld --> ChangeListener

    class -TimerListener {
        + actionPerformed(ActionEvent)
    }
    ActionListener <|-- -TimerListener: implements
    -TimerListener ..> src.CarController
    -TimerListener ..> src.CarViewOld
    -TimerListener ..> src.DrawPanel
    -TimerListener ..> Car
    -TimerListener ..> Volvo240
    -TimerListener ..> Rectangle
    -TimerListener ..> src.CarBrandWorkshop
    -TimerListener ..> JFrame
    
    class src.CarController {
        - int delay
        - Timer timer // -TimerListener
        ~ src.CarViewOld frame
        ~ ArrayList<Car> cars
        ~ src.CarBrandWorkshop<Volvo240> volvoWorkshop
        
        + main(String[]) $
        + getCars() ArrayList<Car>
        + startCars()
        + stopCars()
        + liftBed()
        + lowerBed()
        ~ gas(int)
        ~ brake(int)
        ~ turboOn()
        ~ turboOff()
    }
    src.CarController --* Timer
    src.CarController --* -TimerListener
    src.CarController --* src.CarViewOld
    src.CarController --* Car
    src.CarController --* src.CarBrandWorkshop
    src.CarController ..> Volvo240
    src.CarController ..> Saab95
    src.CarController ..> Scania
```
