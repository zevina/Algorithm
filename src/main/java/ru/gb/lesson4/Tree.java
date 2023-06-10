package ru.gb.lesson4;

import java.util.*;

public class Tree <T extends Comparable<? super T>> { // <T extends Comparable<T>>

    // TODO: 29.05.2023
    //  Добавить возможность использовать Tree для любых сравниваемых типов данных
    //  То есть нужно параметризовать класс T дженериком <T extends Comparable<T>>

    private class Node<T extends Comparable<? super T>> {
        T value;
        Node left;
        Node right;

        public Node(T value) {
            this.value = value;
        }
    }

    private Node root;

    public <T extends Comparable<? super T>> void add(T value) {
        if (root == null) {
            root = new Node<T>(value);
            return;
        }
        add(root, value);
    }

    private <T extends Comparable<? super T>> void add(Node<T> current, T value) {
        if (value.compareTo(current.value) < 0) {
            if (current.left == null) {
                current.left = new Node<T>(value);
            } else {
                add(current.left, value);
            }
        } else if (value.compareTo(current.value) > 0) {
            if (current.right == null) {
                current.right = new Node<T>(value);
            } else {
                add(current.right, value);
            }
        }
    }

    public <T extends Comparable<? super T>> boolean contains(T value) {
        return findNode(root, value) != null;
    }

    private <T extends Comparable<? super T>> Node findNode(Node<T> current, T value) {
        if (current == null) {
            return null;
        }

        if (current.value == value) {
            return current;
        } else if (value.compareTo(current.value) < 0) {
            return findNode(current.left, value);
        } else { // current.value < value
            return findNode(current.right, value);
        }
    }

    public <T extends Comparable<? super T>> void remove(T value) {
        root = removeNode(root, value);
    }

    // Метод, который удаляет ноду и возвращает ту ноду, которая будет вместо удаленной
    private <T extends Comparable<? super T>> Node<T> removeNode(Node<T> current, T value) {
        if (current == null) {
            return null;
        }

        if (value.compareTo(current.value) < 0) {
            current.left = removeNode(current.left, value);
            return current;
        } else if (value.compareTo(current.value) > 0) {
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
        Node<T> smallestNodeOnTheRight = findFirstNode(current.right); // 8
        T smallestValueOnTheRight = smallestNodeOnTheRight.value; // 8
        current.value = smallestValueOnTheRight;
        current.right = removeNode(current.right, smallestValueOnTheRight);
        return current;
    }

    public <T extends Comparable<? super T>> T findFirst() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        return (T) findFirstNode(root).value;
    }

    private  <T extends Comparable<? super T>> Node<T> findFirstNode(Node<T> current) {
        if (current.left != null) {
            return findFirstNode(current.left);
        }
        return current;
    }

    // Поиск в глубину DFS Depth-first search
    // Поиск в ширину  BFS Breath-first search

    public List<T> dfs() {
        if (root == null) {
            return List.of();
        }

        List<T> result = new ArrayList<>();
        dfs(root, result);
        return List.copyOf(result);
    }

    private <T extends Comparable<? super T>> void dfs(Node<T> current, List<T> result) {
        // in-order
        if (current.left != null) {
            dfs(current.left, result);
        }
        result.add(current.value);
        if (current.right != null) {
            dfs(current.right, result);
        }
    }

    public  List<T> bfs() {
        if (root == null) {
            return List.of();
        }

        List<T> result = new ArrayList<>();
        Queue<Node<T>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> next = queue.poll();
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
