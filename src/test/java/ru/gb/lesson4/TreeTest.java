package ru.gb.lesson4;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    @Test
    void testAddAndContains() {
        Tree tree = new Tree();
        tree.add(5);
        tree.add(2);
        tree.add(4);
        tree.add(3);

        assertTrue(tree.contains(5));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(4));
        assertTrue(tree.contains(3));

        assertFalse(tree.contains(1));
        assertFalse(tree.contains(6));
    }

    @Test
    void testAddAndRemove() {
        Tree tree = new Tree();
        tree.add(5);
        tree.add(2);
        tree.add(4);
        tree.add(3);
        tree.add(1);
        tree.add(33);
        tree.add(30);

        assertTrue(tree.contains(4));
        tree.remove(4);
        assertFalse(tree.contains(4));

        tree.remove(5);
        assertFalse(tree.contains(5));
        assertTrue(tree.contains(2));
        assertTrue(tree.contains(3));
        assertTrue(tree.contains(1));
        assertTrue(tree.contains(33));
        assertTrue(tree.contains(30));


        //                  12
        //      6                13
        //   2     8                 14
        //            9
        //               10
        // 2 6 8 9 10 12 13 14
        tree = new Tree();
        tree.add(12);
        tree.add(6);
        tree.add(2);
        tree.add(8);
        tree.add(9);
        tree.add(10);
        tree.remove(6);
        assertFalse(tree.contains(6));
        assertTrue(tree.contains(8));
        assertTrue(tree.contains(9));
        assertTrue(tree.contains(10));

    }

    @Test
    void testFindFirst() {
        Tree tree = new Tree();
        tree.add(12);
        tree.add(6);
        tree.add(2);
        tree.add(8);
        tree.add(9);
        tree.add(10);
        assertEquals(2, tree.findFirst());

        assertThrows(NoSuchElementException.class, () -> new Tree().findFirst());
    }

    @Test
    void testDfs() {
        Tree tree = new Tree();
        tree.add(12);
        tree.add(6);
        tree.add(2);
        tree.add(8);
        tree.add(9);
        tree.add(10);
        tree.add(13);
        tree.add(14);

        List<Integer> dfs = tree.dfs();
        assertIterableEquals(List.of(2, 6, 8, 9, 10, 12, 13, 14), dfs);
    }

    @Test
    void testBfs() {
        Tree tree = new Tree();
        tree.add(12);
        tree.add(6);
        tree.add(2);
        tree.add(8);
        tree.add(9);
        tree.add(10);
        tree.add(13);
        tree.add(14);

        //                  12
        //      6                  13
        //   2     8                    14
        //            9
        //               10
        List<Integer> bfs = tree.bfs();
        assertIterableEquals(List.of(12, 6, 13, 2, 8, 14, 9, 10), bfs);
    }

}