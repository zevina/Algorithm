package ru.gb.lesson3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MyLinkedListTest {

    @Test
    void testToString() {
        MyLinkedList list = new MyLinkedList();
        Assertions.assertEquals("[]", list.toString());

        list.add(1);
        list.add(2);
        list.add(3);
        Assertions.assertEquals("[1 -> 2 -> 3]", list.toString());
    }

    @Test
    void testGet() {
        MyLinkedList list = new MyLinkedList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(-5));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));

        list.add(1);
        list.add(2);
        list.add(3);

        Assertions.assertEquals(2, list.get(1));
        Assertions.assertEquals(1, list.getFirst());
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(3));
    }

    @Test
    void testRevesed() {
        MyLinkedList list = new MyLinkedList();
        MyLinkedList reversed = list.reversed();
        Assertions.assertEquals("[]", reversed.toString());

        list.add(1);
        list.add(2);
        list.add(3);
        reversed = list.reversed();
        Assertions.assertEquals("[1 -> 2 -> 3]", list.toString());
        Assertions.assertEquals("[3 -> 2 -> 1]", reversed.toString());
    }

    @Test
    void testSize() {
        MyLinkedList list = new MyLinkedList();

        list.add(1);
        list.add(2);
        list.add(3);

        Assertions.assertEquals(3, list.size());
    }

    @Test
    void testContains() {
        MyLinkedList list = new MyLinkedList();

        list.add(1);
        list.add(2);
        list.add(3);

        Assertions.assertEquals(false, list.contains(555));
    }

    @Test
    void testSort() {
        MyLinkedList list = new MyLinkedList();

        list.add(7);
        list.add(3);
        list.add(5);
        list.add(1);

        Assertions.assertEquals("[7 -> 3 -> 5 -> 1]", list.toString());
        list.sort();
        Assertions.assertEquals("[1 -> 3 -> 5 -> 7]", list.toString());
    }

}