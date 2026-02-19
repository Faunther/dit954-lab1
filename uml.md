<!-- https://mermaid.js.org/syntax/classDiagram.html -->

```mermaid
classDiagram
    Car <|-- Scania
    Car <|-- Volvo240
    Car <|-- Saab95
    Car <|-- AutoHaler

    CarData <|-- CarHaulerData

    CarData <-- Scania
    CarData <-- Volvo240
    CarData <-- Saab95
    CarHaulerData <-- AutoHaler
    

    class CarHaulerData{
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
        move()*
        turnLeft()*
        turnRight()*
    }

    class IRamp {
        rampDown();
        rampUp();
        getRampIsDown(boolean);
    }
    <<interface>> IMovable
    IMovable <.. Car : implements
    IMovable <.. Saab95 : implements
    IMovable <.. Scania : implements
    IMovable <.. Volvo240 : implements
    IMovable <.. AutoHaler : implements
    CarData *-- Car

    <<interface>> IRamp
    IRamp <.. Scania : implements
    IRamp <.. AutoHaler : implements

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
    class AutoHaler{
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
```
