package com.company;

import java.util.ArrayList;
import java.util.List;

public class Tree_traverse {
    protected List<Integer> path = new ArrayList<>();

    protected List<Integer> path_finder_top_down (List<List<Integer>> sum){
        List<List<Integer>> tree = new ArrayList<>(sum);
        if(path.size() <= 0){
            path = new ArrayList<>(); //Instantiate the array list as starting for the first time
            path.add(0); //adding parent node since only 1 node
            tree.remove(0); // removing parent node
            return path_finder_top_down(tree); //recursive call
        }
        if(sum.size() > 0) {
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
            return path_finder_top_down(tree);
        }
        return path; //tree has been consumed and final value has been obtained
    }

    /*
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
    */
}
