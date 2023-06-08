package ru.gb.lesson3;

public class MyLinkedList {

    /**
     * Реализовать следующие методы
     * 1. public int size() - получить размер списка
     * 2. public boolean contains(int value) - проверить наличие элемента в списке
     * 3.* public MyLinkedList reversed() - создать НОВЫЙ список с обратным порядком
     * 4.** Заменить все int значения на дженерик T
     * 5.* Любые другие доработки, которые захотите для тренировки
     */

    private class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node head;

    public Node getHead() {
        return head;
    }

    /**
     * 1. public int size() - получить размер списка
     */
    public int size() {
        if (head == null) {
            return 0;
        }
        Node current = head;
        int size = 1;
        while (current.next != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    /**
     * 2. public boolean contains(int value) - проверить наличие элемента в списке
     */
    public boolean contains(int value) {
        if (head == null) {
            return false;
        }
        Node current = head;

        while (current.next != null) {
            if (current.value == value) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void sort(){
        sort(getHead(), findLast());
    }

    /**
     * 5.* Любые другие доработки, которые захотите для тренировки
     */
    private void sort(Node start, Node end) {
        if (start == end)
            return;

        // разделить список и рекурсивно разбить на разделы
        Node pivot_prev = partitionLast(start, end);

        sort(start, pivot_prev);

        // если pivot выбран и перемещен в начало, это означает, что start и pivot совпадают, поэтому выбрать из следующего pivot
        if (pivot_prev != null && pivot_prev == start)
            sort(pivot_prev.next, end);

            // если pivot находится посередине списка, начать со следующего из pivot, так как у нас есть pivot_prev, поэтому перемещаем два узла
        else if (pivot_prev != null
                && pivot_prev.next != null)
            sort(pivot_prev.next.next, end);

    }

    private Node partitionLast(Node start, Node end) {
        if (start == end || start == null || end == null)

            return start;

        Node pivot_prev = start;
        Node curr = start;
        int pivot = end.value;

        // повторять до одного перед концом, нет необходимости повторять до конца, потому что конец является поворотным
        while (start != end) {
            if (start.value < pivot) {
                // отслеживать последний измененный элемент
                pivot_prev = curr;
                int temp = curr.value;
                curr.value = start.value;
                start.value = temp;
                curr = curr.next;
            }

            start = start.next;
        }

        // поменять местами положение текущего, т.е. следующего подходящего индекса и pivot
        int temp = curr.value;
        curr.value = pivot;
        end.value = temp;

        // вернуть предыдущее значение в текущее, потому что текущее теперь указывает на pivot
        return pivot_prev;
    }


    public void add(int value) {
        if (head == null) {
            head = new Node(value);
        } else {
            Node last = findLast();
            last.next = new Node(value);
        }
    }

    public int getFirst() {
        return get(0);
    }

    public int get(int index) {
        if (index < 0 || head == null) {
            throw new IndexOutOfBoundsException(index);
        }

        Node current = head;
        int currentIndex = 0;
        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }
        if (currentIndex == index && current != null) {
            return current.value;
        }
        throw new IndexOutOfBoundsException(index);
    }

    public int popFirst() {
        return pop(0);
    }

    public int pop(int index) {
        if (index < 0 || head == null) {
            throw new IndexOutOfBoundsException(index);
        }

        if (index == 0) {
            int pop = head.value;
            head = head.next;
            return pop;
        }

        Node previous = head; // предыдущая от искомой
        int currentIndex = 1;
        while (previous.next != null) {
            if (currentIndex == index) {
                int pop = previous.next.value;
                previous.next = previous.next.next;
                return pop;
            }

            previous = previous.next;
            currentIndex++;
        }
        throw new IndexOutOfBoundsException(index);
    }

    public MyLinkedList reversed() {
        MyLinkedList reversedList = new MyLinkedList();
        if (head == null) {
            return reversedList;
        }
        addReversedRecursive(head, reversedList);
        return reversedList;
    }

    private void addReversedRecursive(Node current, MyLinkedList result) {
        if (current.next != null) {
            addReversedRecursive(current.next, result);
        }
        result.add(current.value);
    }

    private Node findLast() {
        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        Node current = head;
        while (current != null) {
            result.append(current.value).append(" -> ");
            current = current.next;
        }

        int length = result.length();
        if (length > 4) {
            result.delete(length - 4, length);
        }

        result.append("]");
        return result.toString();
    }
}
