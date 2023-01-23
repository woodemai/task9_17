package ru.vsu.cs.savchenko_n_a.task9_17;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    public static List<Integer> arrToList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int j : arr) {
            list.add(j);
        }
        return list;
    }

    public static int[] listToArr(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }
}
