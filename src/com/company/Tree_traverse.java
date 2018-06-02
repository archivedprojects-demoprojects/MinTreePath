package com.company;

import java.util.ArrayList;
import java.util.List;

public class Tree_traverse {
    protected List<Integer> top_down_traversal (List<List<Integer>> tree){
        List<Integer> result = new ArrayList<>();
        int currentIndex = 0;
        for (List<Integer> aTree : tree) {
            for (int j = 0; j < aTree.size(); j++) {
                if (aTree.get(j) < aTree.get(currentIndex)) {
                    currentIndex = j;
                }
            }
            result.add(currentIndex);
        }
        return result;
    }
}
