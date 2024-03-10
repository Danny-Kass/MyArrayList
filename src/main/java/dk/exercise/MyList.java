package dk.exercise;

import java.util.Comparator;

/**
 * Упорядоченная коллекция (также известная как <i>последовательность</i>).
 * Пользователь этого интерфейса имеет точный контроль над тем, где в списке вставляется каждый элемент.
 * Пользователь может получать доступ к элементам по их целочисленному индексу (позиции в списке)
 * и осуществлять поиск элементов в списке.<p>
 *
 * @param <T> тип элементов в этом списке.
 */
public interface MyList<T> {
    T set(int index, T element);

    void add(T element);

    void add(int index, T element);

    T get(int index);

    void remove(int index);

    void clear();

    void sort();

    void sort(Comparator<T> comparator);

    int size();

    Object[] toArray();

    boolean addAll(MyList<? extends T> c);
}
