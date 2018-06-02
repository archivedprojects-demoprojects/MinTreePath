package com.company;

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

    protected void array_print1(List<List<Integer>> content) {
        if (content.size() > 0) {
            //System.out.println(content.get(0));
            System.out.println(content.get(0).stream().map(i -> i.toString()).collect( Collectors.joining(" ","","")));
            content.remove(0);
            array_print1(content);
        }else{
            System.out.println("EOF");
        }
    }
}
