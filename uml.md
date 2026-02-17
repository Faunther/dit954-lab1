<!-- https://mermaid.js.org/syntax/classDiagram.html -->

```mermaid
classDiagram
    Car <|-- Scania
    Car <|-- Volvo
    Car <|-- Saab95

    class CarData {
        #double m_enginePower
        #int m_nrDoors
        #String m_modelName

        getNrDoors() int
        getEnginePower() double
        getModelName() String
    }

    class IMovable {
        move()*
        turnLeft()*
        turnRight()*
    }
    <<interface>> IMovable
    IMovable <|-- Car : implements
    CarData *-- Car

    class Car {
        #CarData m_carData
        #Color m_color
        #double m_currentSpeed
        #Point2D.Double m_position
        #double m_direction
        
        move()
        turnLeft()
        turnRight()
        getPoint() Point2D.Double
        getDirection() double
        getNrDoors() int
        getModelName() String
        getEnginePower() double
        getCurrentSpeed() double
        getColor() Color
        setColor(Color newColor)

        startEngine()
        stopEngine()
        gas()
        brake()

        #speedFactor()* double
    }
    class Scania {
        rampUp()
        rampDown()
    }
    class Volvo {
        double trimLevel
        setTrimLevel(double newLevel)
    }
    class Saab95 {
        boolean turbo
        setTurboOn()
        setTurboOff()
    }
```
