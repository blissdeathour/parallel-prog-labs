package lab3;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorContainer<T> {
    ArrayList<T> container;

    IteratorContainer() {
        container = new ArrayList<>();
    }

    IteratorContainer(T v) {
        container = new ArrayList<>();
        container.add(v);
    }

    public Iterator<T> getIterator() {
        return (container.iterator());
    }
}
