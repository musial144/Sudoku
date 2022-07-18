package pl.kompo;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Dao<T> {

    T read() throws FileNotFoundException, IOException, ClassNotFoundException, Throwable;

    void write(T obj) throws Throwable;
}
