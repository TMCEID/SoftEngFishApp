// src/model/SortStrategy.java
package model;

import java.util.List;

@FunctionalInterface
public interface SortStrategy<T> {
    List<T> sort(List<T> items);
}
