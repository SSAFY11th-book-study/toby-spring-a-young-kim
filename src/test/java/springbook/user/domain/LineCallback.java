package springbook.user.domain;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
