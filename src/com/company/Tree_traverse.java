package com.company;

import java.util.ArrayList;
import java.util.List;

public class Tree_traverse {
    private List<Integer> path = new ArrayList<>();

    protected List<Integer> top_down_traversal_old (List<List<Integer>> tree){
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

    protected List<Integer> top_down_sum (List<List<Integer>> sum, boolean first){
        if(first){
            path = new ArrayList<>();
        }
        if(sum.size() > 0) {
            List<List<Integer>> tree = new ArrayList<>(sum);
            if (path.size() <= 0) {
                path.add(0);
                tree.remove(0);
                top_down_sum(tree, false);
            } else {
                /*
                 * get last value of the path
                 * add +1 to the value, check last value of path and the next index:
                 * Example:
                 *   If index is 1, can have children of index 1 or  2
                 * */
                int compare_index = path.get(path.size()-1);
                int compare_next_index = compare_index + 1;
                if (sum.get(0).get(compare_index) < sum.get(0).get(compare_next_index)) {
                    path.add(compare_index);
                }else{
                    path.add(compare_next_index);
                }
                tree.remove(0);
                top_down_sum(tree, false);
            }
        }
        return path;
    }
}
