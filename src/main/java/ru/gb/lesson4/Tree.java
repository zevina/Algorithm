package ru.gb.lesson4;

import java.util.*;

public class Tree { // <T extends Comparable<T>>

    // TODO: 29.05.2023
    //  Добавить возможность использовать Tree для любых сравниваемых типов данных
    //  То есть нужно параметризовать класс T дженериком <T extends Comparable<T>>

    private class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    private Node root;

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        add(root, value);
    }

    private void add(Node current, int value) {
        if (value < current.value) {
            if (current.left == null) {
                current.left = new Node(value);
            } else {
                add(current.left, value);
            }
        } else if (value > current.value) {
            if (current.right == null) {
                current.right = new Node(value);
            } else {
                add(current.right, value);
            }
        }
    }

    public boolean contains(int value) {
        return findNode(root, value) != null;
    }

    private Node findNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (current.value == value) {
            return current;
        } else if (current.value > value) {
            return findNode(current.left, value);
        } else { // current.value < value
            return findNode(current.right, value);
        }
    }

    public void remove(int value) {
        root = removeNode(root, value);
    }

    // Метод, который удаляет ноду и возвращает ту ноду, которая будет вместо удаленной
    private Node removeNode(Node current, int value) {
        if (current == null) {
            return null;
        }

        if (value < current.value) {
            current.left = removeNode(current.left, value);
            return current;
        } else if (value > current.value) {
            current.right = removeNode(current.right, value);
            return current;
        }

        // Нужно удалить current. Возможны 3 случая.
        // 1. У удаляемого узла нет дочерних узлов
        if (current.left == null && current.right == null) {
            return null;
        }

        // 2. У удаляемого узла ровно 1 дочерний узел
        if (current.left != null && current.right == null) {
            return current.left;
        } else if (current.left == null && current.right != null) {
            return current.right;
        }

        // 3. У удаляемого узла 2 дочерних узла
        // Ищем минимальный элемент в правом поддереве

        //          12
        //      8
        //   2     9
        //           10
        //
        // current = 6
        Node smallestNodeOnTheRight = findFirst(current.right); // 8
        int smallestValueOnTheRight = smallestNodeOnTheRight.value; // 8
        current.value = smallestValueOnTheRight;
        current.right = removeNode(current.right, smallestValueOnTheRight);
        return current;
    }

    public int findFirst() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return findFirst(root).value;
    }

    private Node findFirst(Node current) {
        if (current.left != null) {
            return findFirst(current.left);
        }
        return current;
    }

    // Поиск в глубину DFS Depth-first search
    // Поиск в ширину  BFS Breath-first search

    public List<Integer> dfs() {
        if (root == null) {
            return List.of();
        }

        List<Integer> result = new ArrayList<>();
        dfs(root, result);
        return List.copyOf(result);
    }

    private void dfs(Node current, List<Integer> result) {
        // in-order
        if (current.left != null) {
            dfs(current.left, result);
        }
        result.add(current.value);
        if (current.right != null) {
            dfs(current.right, result);
        }
    }

    public List<Integer> bfs() {
        if (root == null) {
            return List.of();
        }

        List<Integer> result = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node next = queue.poll();
            result.add(next.value);
            if (next.left != null) {
                queue.add(next.left);
            }
            if (next.right != null) {
                queue.add(next.right);
            }
        }
        return result;
    }

}
