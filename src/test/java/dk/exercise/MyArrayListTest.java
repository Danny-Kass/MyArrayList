package dk.exercise;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyArrayListTest {
    MyList<Integer> testList;

    @BeforeEach
    void setUp(){
        testList = new MyArrayList<>();
        testList.add(1);
    }

    @Test
    void addAndGet() {
        assertEquals(1, testList.get(0));
        assertEquals(1, testList.size());
    }

    @Test
    void addIndex() {
        testList.add(0, 2);
        assertEquals(2, testList.get(0));
        assertEquals(1, testList.get(1));
    }

    @Test
    void remove() {
        testList.remove(0);
        assertEquals(0, testList.size());
    }

    @Test
    void clear() {
        testList.clear();
        assertEquals(0, testList.size());
    }

    @Test
    void sort() {
        testList.add(3);
        testList.add(2);
        testList.sort();
        assertEquals(1, testList.get(0));
        assertEquals(2, testList.get(1));
        assertEquals(3, testList.get(2));
    }

    @Test
    void sortComparator() {
        testList.add(3);
        testList.add(2);
        testList.sort(Integer::compareTo);
        assertEquals(1, testList.get(0));
        assertEquals(2, testList.get(1));
        assertEquals(3, testList.get(2));
    }

    @Test
    void set() {
        Integer oldValue = testList.set(0, 2);
        assertEquals(1, oldValue);
        assertEquals(2, testList.get(0));
    }

    @Test
    void addAll() {
        MyList<Integer> addList = new MyArrayList<>();
        addList.add(2);
        addList.add(3);
        testList.addAll(addList);
        assertEquals(1, testList.get(0));
        assertEquals(2, testList.get(1));
        assertEquals(3, testList.get(2));
    }

    @Test
    void toArray() {
        testList.add(2);
        testList.add(3);
        Object[] testArray = testList.toArray();
        assertEquals(testList.get(0), testArray[0]);
        assertEquals(testList.get(1), testArray[1]);
        assertEquals(testList.get(2), testArray[2]);
    }

    @Test
    void addException() {
        assertThrows(IndexOutOfBoundsException.class, () -> testList.add(2, 2));
    }
}