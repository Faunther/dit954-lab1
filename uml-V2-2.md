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
o--	    Aggregation (A owns B, B can be independent)
-->     Association
--      Link (Solid)
..>     Dependency
..|>    Realization
..      Link (Dashed)
```

```mermaid
---
config:
    look: classic
    displayMode: compact
    layout: elk
    elk:
        mergeEdges: true
        nodePlacementStrategy: SIMPLE
---

classDiagram

namespace Interfaces {
  class IRamp
  class IMovable
  class ITurboCharger   
}

namespace CarSystems {
    class CarSystem
    class CarSystemFactory
    class ScaniaSystem
    class VolvoSystem
    class SaabSystem
}

namespace Models {
    class Car
    class src.CarBrandWorkshop
    class CarFactory
    class Volvo240
    class Saab95
    class Scania
    class AutoHauler
}
  class CarSystem{
    +updateCar()
    +carList()
    +getGasOnCallback(): ActionListener
    -onGasEvent()
  }
  CarSystem <|-- SaabSystem
  CarSystem <|-- VolvoSystem
  CarSystem <|-- ScaniaSystem

  CarSystem <|-- SaabSystem
  SaabSystem ..> Saab95

  class SaabSystem{
    +carlist

    +updateCarList()
    +updateCar()
    +carList()
    +getTurboOffOnCallback(): ActionListener
    -turboOffEvent()
    +getTurboOnOnCallback(): ActionListener
    -turboOnEvent()

  }
  CarSystem <|-- VolvoSystem
  VolvoSystem ..> Volvo240

  class VolvoSystem{
    +carlist

    +updateCarList()
    +updateCar()
    +carList()
  }
  CarSystem <|-- ScaniaSystem
  ScaniaSystem ..> Scania

    class ScaniaSystem{
        +carlist

        +updateCarList()
        +updateCar()
        +carList()
        +getliftBedOnCallback(): ActionListener
        -liftBedEvent()
        +getlowerBedOnCallback(): ActionListener
        -lowerBedEvent()

    }
    CarSystemFactory --> SaabSystem
    CarSystemFactory --> VolvoSystem
    CarSystemFactory --> ScaniaSystem

    class CarSystemFactory{
        +createVolvoSystem()
        +createSaabSystem()
        +createScaniaSystem()
    }

    Car <|-- Scania: extends
    Car <|-- Volvo240: extends
    Car <|-- Saab95: extends
    Car <|-- AutoHauler: extends

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
    class ITurboCharger {
        +setTurboOn()
        +setTurboOff()
    }
    <<interface>> ITurboCharger
    ITurboCharger <|.. Saab95: implements
    
    <<interface>> IMovable
    IMovable <|.. Car: implements
    IMovable <|.. Saab95: implements
    IMovable <|.. Scania: implements
    IMovable <|.. Volvo240: implements
    IMovable <|.. AutoHauler: implements
    
    <<interface>> IRamp
    IRamp <|.. Scania: implements
    IRamp <|.. AutoHauler: implements

    class Car {
        #Color m_color
        #double m_currentSpeed
        #Point2D.Double m_position
        #double m_direction
        +move()
        +turnLeft()
        +turnRight()
        +getPoint() Point2D.Double
        +getDirection() double
        +getNrDoors()* int
        +getModelName()* String
        +getEnginePower()* *double
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

        +getNrDoors() int
        +getEnginePower() double
        +getModelName() String
    }
    class Volvo240 {
        #int m_nrDoors
        #double m_enginePower
        #String m_modelName
        +Color color
        +double trimFactor
        +speedFactor(double)
        +getNrDoors() int
        +getEnginePower() double
        +getModelName() String
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
        +getNrDoors() int
        +getEnginePower() double
        +getModelName() String
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
        +getNrDoors() int
        +getEnginePower() double
        +getModelName() String
    }

  CarFactory ..> Volvo240
  CarFactory ..> Saab95
  CarFactory ..> Scania

  class CarFactory {
    +createCar(string)
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

namespace GUI {
    class src.DrawPanel
    class src.CarViewOld
    class src.CarController
}

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

        ~ getAreaSize(Car) Dimensions
        ~ getCarSize(Car) Dimensions
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

    class src.CarController {
        - int delay
        - Timer timer // -TimerListener
        ~ src.CarViewOld frame
        ~ ArrayList<Car> cars
        ~ src.CarBrandWorkshop<Volvo240> volvoWorkshop
        
        + getCars() ArrayList<Car>
        + startCars()
        + stopCars()
        + liftBed()
        + lowerBed()
        ~ gas(int)
        ~ brake(int)
        ~ turboOn()
        ~ turboOff()

        - actionPerformed(ActionEvent)
    }
    src.CarController --* Timer
    src.CarController --* -TimerListener
    src.CarController --* src.CarViewOld

    src.CarController --* src.CarBrandWorkshop
    src.CarController --o CarSystem
    src.CarController ..> Rectangle
    src.CarController ..> IRamp
    src.CarController ..> IMovable
    src.CarController ..> ITurboCharger
    
    class Application {
        + main(String[]) $
    }
    Application ..> src.CarBrandWorkshop
    Application .. CarFactory
    Application .. CarSystemFactory
    Application ..* CarSystem
    Application ..* src.CarController
```

# Ändringar sedan V1:

- Tog bort CarData. La till getNrDoors, getEnginePower, getModelName istället
- Tog bort relation mellan src.CarController/TimerListener och src.DrawPanel - ersätts
  med frame.getPanelSize() och frame.getCarSize()
- Lagt till Application class
- Har skapat en CarFactory so att Application bara interagerar med en class när
  den skapar sina Car.
