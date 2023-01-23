package ru.vsu.cs.savchenko_n_a.task9_17;

import java.util.List;

public class Task {


    public static void sort(List<Integer> list, int index1, int index2) {
        int first, last;
        int num;
        boolean direction;
        int n;
        direction = index1 < index2;
        if (direction) {
            first = index1;
            last = index2;
            n = 1;
        } else {
            first = index2;
            last = index1;
            n = -1;
        }
        if (first < 0) {
            first = 0;
        }
        if (last >= list.size()) {
            last = list.size() - 1;
        }
        for (int i = 0; i < last - first; i++) {
            for (int j = first; j < last - i; j++) {
                if (list.get(j) * n > list.get(j + 1) * n) {
                    num = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, num);
                }
            }
        }
    }
}
