package ru.gb.lesson4;

public class Lesson4Main {

    public static void main(String[] args) {
        Integer x = 7;
        Integer y = 4;

        System.out.println(x.compareTo(y)); // отрицательное

        System.out.println("abcde".compareTo("sdscdewr"));

        // http -> ssh
    }

    private <T extends Comparable<? super T>> void foo(T x, T y) {
        // x < y ~ x.compareTo(y) < 0
        // x == y ~ x.compareTo(y) == 0
        // x > y ~ x.compareTo(y) > 0
    }

}
