<!-- https://mermaid.js.org/syntax/classDiagram.html -->

```mermaid
classDiagram
    Car <|-- Scania
    Car <|-- Volvo
    Car <|-- Saab95

    class Car {
        Point position
        double direction
        gas()
        brake()
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
