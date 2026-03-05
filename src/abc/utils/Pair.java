package src.abc.utils;

public class Pair<T, Y> {
    T v1;
    Y v2;

    public Pair(T a, Y b) {
        v1 = a;
        v2 = b;
    }

    public T getFirst() {
        return v1;
    }

    public Y getSecond() {
        return v2;
    }
}
