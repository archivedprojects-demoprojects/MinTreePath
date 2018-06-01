package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.lang.Integer.min;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        List<List<Integer>> content = file_read("data_test.txt");
        if(content != null) {
            bottom_up_approach_naive(content);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Duration: "+duration/1000000+"ms");
    }

    private static List<List<Integer>> file_read(String file_name){
        String line;
        int[] line_content;
        List<List<Integer>> content = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file_name);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //TODO - check how to optimise loop below
            while((line = bufferedReader.readLine()) != null) {
                //Using Lambda Expressions
                line_content = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                //line_content = Integer.parseInt(line.split(" ")); - Old approach using Strings
                content.add(IntStream.of(line_content).boxed().collect(Collectors.toList()));
            }

            bufferedReader.close();
            return content;

        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + file_name + "'");
            return null;
        }catch(IOException ex) {
            System.out.println("Error reading file '" + file_name + "'");
            return null;
        }
    }

    //TODO - Function to be optimised - remove for loop, use higher order
    private static void array_print(List<List<Integer>> content){
        for (List<Integer> rows : content) {
            for (int values : rows) {
                System.out.print(values + " ");
            }
            System.out.println();
        }
    }

    private static void bottom_up_approach_naive(List<List<Integer>> content){
        List<List<Integer>> bottom = new ArrayList<>(content);
        List<List<Integer>> shortest_paths = new ArrayList<>();

        List<Integer> best_values = new ArrayList<>();

        Collections.reverse(bottom);
        array_print(bottom);
        array_print(content);

        //from the leave we can go parent having the same index or index - 1
        //ie if we are at leaf 4, I can go to the following parent notes: 3,4
        shortest_paths.add(bottom.get(0));

        for (int i =0; i <bottom.size(); i++) {
            List<Integer> row = new ArrayList<>(bottom.get(i));
            if(i != bottom.size()-1) {
                if(!best_values.isEmpty()){
                    List<Integer> current_row = new ArrayList<>(bottom.get(i));
                    List<Integer> best = new ArrayList<>(best_values);
                    List<Integer> result = IntStream.range(0, current_row.size()).mapToObj(k -> current_row.get(k) + best.get(k)).collect(Collectors.toList()); // Sum of the best values and current row
                    System.out.println();
                    System.out.println("Sum:" + result);
                    best_values.clear();
                    row = new ArrayList<>(result);
                    shortest_paths.add(row);
                }
                for (int j = 0; j < row.size(); j++) {
                    //always have to compare values with their neighbouring cells
                    int current_value = row.get(j);
                    if(j != row.size() - 1){
                        int next_value = row.get(j+1);
                        System.out.println("Current: " + current_value);
                        System.out.println("Next: " + next_value);
                        best_values.add(min(current_value,next_value));
                    }
                }
                System.out.println(best_values);
            }else{
                System.out.println("Parent: "+ bottom.get(i).get(0));
                shortest_paths.add(best_values);
                break;
            }
        }
        Collections.reverse(shortest_paths);
        //array_print(shortest_paths);
        List<Integer> shortest_path_index = top_down_traversal(shortest_paths);
        List<Integer> shortest_path_values = new ArrayList<>();

        for(int i = 0; i < content.size(); i++){//getting the values of the tree from the index
            shortest_path_values.add(content.get(i).get(shortest_path_index.get(i)));
        }
        array_print(content);
        System.out.println("Shortest Path Traversal (Index): " + shortest_path_index);
        System.out.println("Shortest Path Traversal (Values): " + shortest_path_values);
        System.out.println("Shortest Path Sum: "+(content.get(0).get(0) + best_values.get(0)));
    }

    private static List<Integer> top_down_traversal (List<List<Integer>> tree){
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
