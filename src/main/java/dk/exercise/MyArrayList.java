package dk.exercise;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Реализация интерфейса {@code MyList} с изменяемым размером массива.
 * Реализует все необязательные операции со списком и разрешает все элементы, включая {@code null}.
 * Помимо реализации интерфейса {@code MyList},
 * этот класс предоставляет методы для управления размером массива,
 * который используется внутри для хранения.
 *
 * <p>Операции {@code size}, {@code get} и {@code set} выполняются за постоянное время.
 * Операция {@code add} выполняется за <i>амортизированное постоянное время</i>,
 * то есть добавление n элементов требует времени O(n).
 * Все остальные операции выполняются за линейное время (грубо говоря).
 *
 * <p>Каждый экземпляр {@code MyArrayList} имеет <i>емкость</i>.
 * Емкость — это размер массива, используемого для хранения элементов списка.
 * Он всегда не меньше размера списка.
 * По мере добавления элементов в {@code MyArrayList} его емкость автоматически увеличивается.
 *
 * @param <T> тип элементов в этом списке
 */
@SuppressWarnings("unchecked")
public class MyArrayList<T> implements MyList<T> {
    /**
     * Начальная емкость по умолчанию.
     */
    private static final int CAPACITY = 10;

    /**
     * Общий пустой экземпляр массива, используемый для пустых экземпляров MyArrayList.
     */
    private static final Object[] EMPTY_DATA = {};

    /**
     * Буферный массив, в котором хранятся элементы MyArrayList.
     * Емкость MyArrayList — это длина этого буферного массива.
     */
    private Object[] data;

    /**
     * Размер MyArrayList (количество содержащихся в нем элементов).
     */
    private int size;

    /**
     * Создает пустой список с начальной емкостью десять элементов.
     */
    public MyArrayList() {
        this.data = new Object[CAPACITY];
        this.size = 0;
    }

    /**
     * Создает пустой список с указанной начальной емкостью.
     *
     * @param initialCapacity начальная емкость списка.
     * @throws IllegalArgumentException если указанная начальная емкость отрицательна.
     */
    public MyArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.data = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.data = EMPTY_DATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " +
                    initialCapacity);
        }
    }

    /**
     * Заменяет элемент в указанной позиции в этом списке на
     * заданный элемент.
     *
     * @param index индекс элемента для замены
     * @param element элемент, который будет сохранен в указанной позиции
     * @return элемент, ранее находившийся в указанной позиции
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = (T) data[index];
        data[index] = element;
        return oldValue;
    }

    /**
     * Добавляет указанный элемент в конец этого списка.
     *
     * @param element элемент, который будет добавлен в этот список
     */
    @Override
    public void add(T element) {
        if (size == data.length)
            grow();
        data[size] = element;
        size++;
    }

    /**
     * Вставляет указанный элемент в указанную позицию в этом списке.
     * Смещает элемент, находящийся в данный момент в этой позиции (если есть),
     * и любые последующие элементы вправо (добавляет единицу к их индексам).
     *
     * @param index индекс, в который должен быть вставлен указанный элемент
     * @param element элемент, который нужно вставить
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void add(int index, T element) {
        checkIndex(index);
        if (size == data.length)
            grow();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    /**
     * Возвращает элемент в указанной позиции в этом списке.
     *
     * @param  index индекс возвращаемого элемента
     * @return элемент в указанной позиции в этом списке
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    /**
     * Удаляет элемент в указанной позиции в этом списке.
     * Сдвигает любые последующие элементы влево (вычитает единицу из их индексов).
     *
     * @param index индекс элемента, который нужно удалить
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    @Override
    public void remove(int index) {
        checkIndex(index);
        final int newSize = size - 1;
        if (newSize > index)
            System.arraycopy(data, index + 1, data, index, newSize - index);
        data[size = newSize] = null;
    }

    /**
     * Удаляет все элементы из этого списка.
     * Список будет пуст после вызова этого метода.
     */
    @Override
    public void clear() {
        this.data = new Object[CAPACITY];
        size = 0;
    }

    /**
     * Сортирует этот список элементов по возрастанию
     * в соответствии с естественным порядком его элементов.
     * Элементы списка должны имплементировать интерфейс {@code Comparable}.
     *
     */
    @Override
    public void sort() {
        Arrays.sort(data, 0, size);
    }

    /**
     * Сортирует этот список элементов в соответствии с порядком,
     * заданным указанным {@code Comparator}.
     *
     * @param comparator компаратор в соответствии с которым сортируются элементы
     */
    @Override
    public void sort(Comparator<T> comparator) {
        Arrays.sort((T[]) data, 0, size, comparator);
    }

    /**
     * Возвращает количество элементов в этом списке.
     *
     * @return количество элементов в этом списке.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Возвращает массив, содержащий все элементы этого списка
     * в правильной последовательности (от первого до последнего элемента).
     *
     * <p>Возвращенный массив будет «безопасным»,
     * поскольку в этом списке не сохраняются никакие ссылки на него.
     * (Другими словами, этот метод должен выделить новый массив).
     * Таким образом, вызывающая сторона может изменять возвращаемый массив.
     *
     * @return массив, содержащий все элементы этого списка в правильной последовательности
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(data, size);
    }

    /**
     * Добавляет все элементы указанного списка в конец этого списка.
     *
     * @param list список, содержащий элементы, которые нужно добавить в этот список
     * @return {@code true} если этот список изменился в результате вызова
     * @throws NullPointerException если указанный список имеет значение null
     */
    @Override
    public boolean addAll(MyList<? extends T> list) {
        Object[] a = list.toArray();
        int numNew = a.length;
        if (numNew == 0)
            return false;
        if (numNew > data.length - size)
            data = Arrays.copyOf(data, data.length + numNew);
        System.arraycopy(a, 0, data, size, numNew);
        size = size + numNew;
        return true;
    }


    /**
     * Обрезает емкость этого экземпляра {@code MyArrayList}
     * в соответствии с текущим размером списка.
     * Приложение может использовать эту операцию,
     * чтобы минимизировать объем памяти экземпляра {@code MyArrayList}.
     */
    public void trimToSize() {
        if (size < data.length) {
            data = (size == 0) ? EMPTY_DATA : Arrays.copyOf(data, size);
        }
    }

    /**
     * Увеличивает емкость этого экземпляра {@code MyArrayList}
     * на размер {@code CAPACITY}.
     * Используется для автоматического увеличения объёма списка.
     */
    private void grow() {
        data = Arrays.copyOf(data, data.length + CAPACITY);
    }

    /**
     * Проверяет указанный индекс на соответствие размеру этого списка.
     * Используется в методах get, add, set, remove.
     *
     * @param index индекс элемента списка.
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    private void checkIndex(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
}
