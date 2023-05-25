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

}