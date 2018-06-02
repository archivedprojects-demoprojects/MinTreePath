package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Visualisation {
    //TODO - Function to be optimised - remove for loop, use higher order
    protected void array_print(List<List<Integer>> content){
        for (List<Integer> rows : content) {
            for (int values : rows) {
                System.out.print(values + " ");
            }
            System.out.println();
        }
    }

    protected void tree_print(List<List<Integer>> content) {
        if (content.size() > 0) {
            List<List<Integer>> tree = new ArrayList<>(content);
            //System.out.println(content.get(0));
            System.out.println(tree.get(0).stream().map(Object::toString).collect( Collectors.joining(" ","","")));
            tree.remove(0);
            tree_print(tree);
        }else{
            System.out.println("EOF");
        }
    }
}
