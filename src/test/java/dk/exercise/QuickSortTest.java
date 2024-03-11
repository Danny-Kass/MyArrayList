package dk.exercise;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortTest {

    @Test
    void quickSort() {
        MyList<Integer> testList = new MyArrayList<>();
        testList.add(10);
        testList.add(5);
        testList.add(2);
        testList.add(7);
        testList.add(9);
        testList.add(3);
        testList.add(8);
        testList.add(6);
        testList.add(4);

        QuickSort.quickSort(testList);

        assertEquals(2, testList.get(0));
        assertEquals(3, testList.get(1));
        assertEquals(4, testList.get(2));
        assertEquals(5, testList.get(3));
        assertEquals(6, testList.get(4));
        assertEquals(7, testList.get(5));
        assertEquals(8, testList.get(6));
        assertEquals(9, testList.get(7));
        assertEquals(10, testList.get(8));
    }
}