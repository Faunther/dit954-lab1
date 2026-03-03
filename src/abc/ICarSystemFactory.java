package src.abc;

public interface ICarSystemFactory {
    CarSystem<?> createSystem(String type) throws IllegalArgumentException;
}
