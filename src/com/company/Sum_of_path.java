package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.lang.Integer.min;

public class Sum_of_path {
    private final Tree_traverse tree_traverse = new Tree_traverse();
    private final Visualisation visualisation = new Visualisation();

    private final List<Integer> best_values = new ArrayList<>();

    protected void result(List<List<Integer>> content){
        List<List<Integer>> bottom = new ArrayList<>(content);
        Collections.reverse(bottom); //parent node is now at the bottom
        List<List<Integer>> sum_tree = sum_tree_gen(bottom,new ArrayList<>());
        Collections.reverse(sum_tree); //parent node at the top again in our result tree
        sum_tree.add(bottom.get(0)); //adding the leaf nodes (end)

        tree_traverse.path = new ArrayList<>(); //Instantiating path
        List<Integer> shortest_path_index = tree_traverse.path_finder_top_down(sum_tree); //finding the optimal path

        //Since the path_finder algorithm returns the index of the tree not the actual value,
        //We use the result to check the original tree according to their index and save that
        List<Integer> shortest_path_values = new ArrayList<>();
        for(int i = 0; i < content.size(); i++){
            shortest_path_values.add(content.get(i).get(shortest_path_index.get(i)));//getting the values of the tree from the index
        }

        visualisation.tree_print(content);
        System.out.println("Minimum path is: " + shortest_path_values.stream().map(Object::toString).collect( Collectors.joining(" + ",""," = "))+ shortest_path_values.stream().mapToInt(Integer::intValue).sum());
    }

    private List<List<Integer>> sum_tree_gen(List<List<Integer>>tree, List<List<Integer>>sum){
        if(tree.size() > 0) {
            List<List<Integer>> current_tree = new ArrayList<>(tree);
            List<Integer> current_row = current_tree.get(0);

            for (int i = 0; i < current_row.size(); i++) {
                /*
                 * always have to compare values with their neighbouring cell
                 * from the leave we can go parent having the same index or index - 1
                 * i.e. if we are at leaf 4, I can go to the following parent notes: 3,4
                 * last value does not have to be checked as it is being checked with the value before that)
                 * */
                int current_value = current_row.get(i);
                if (i != current_row.size() - 1) {
                    best_values.add(min(current_value, current_row.get(i + 1)));
                }
            }
            current_tree.remove(0);

            if(tree.size() != 1) {
                sum.add(IntStream.range(0, current_tree.get(0).size()).mapToObj(k -> current_tree.get(0).get(k) + best_values.get(k)).collect(Collectors.toList()));// Sum of the best values and current row
                best_values.clear();
            }
            return sum_tree_gen(current_tree, sum);
        }
        return sum;
    }

    /*
     * Method break down;
     * Obtain list of contents(parameter)
     *   Inverse it (top to bottom and vs versa (leaf order not effected))
     * Create a new tree (Sum)
     *   Said tree will calculate the sum of the leaf
     *   It will check the least expensive path to take between the two parent nodes and add that as its preferred parent
     *   This will be calculated starting from the bottom and working upwards
     * From the new tree we inverse it again (parent on top - same as we started)
     *   We then traverse the tree from top to bottom taking the least expensive leaves
     *   The index of the new tree throughout traversal is stored
     *   Afterward from these indices we can extract the values of the original tree (path taken)
     * Time Complexity: O(n^2)
     *
    protected void bottom_up_calc_top_down_traverse(List<List<Integer>> content){
        List<List<Integer>> bottom = new ArrayList<>(content);
        List<List<Integer>> shortest_paths = new ArrayList<>();

        List<Integer> best_values = new ArrayList<>();

        Collections.reverse(bottom);
        shortest_paths.add(bottom.get(0));

        for (int i =0; i <bottom.size(); i++) {
            List<Integer> row = new ArrayList<>(bottom.get(i));
            if(i != bottom.size()-1) {
                if(!best_values.isEmpty()){
                    List<Integer> current_row = new ArrayList<>(bottom.get(i));
                    List<Integer> best = new ArrayList<>(best_values);
                    List<Integer> result = IntStream.range(0, current_row.size()).mapToObj(k -> current_row.get(k) + best.get(k)).collect(Collectors.toList()); // Sum of the best values and current row
                    best_values.clear();
                    row = new ArrayList<>(result);
                    shortest_paths.add(row);
                }
                for (int j = 0; j < row.size(); j++) {
                    //always have to compare values with their neighbouring cells
                    //from the leave we can go parent having the same index or index - 1
                    //i.e. if we are at leaf 4, I can go to the following parent notes: 3,4
                    int current_value = row.get(j);
                    if(j != row.size() - 1){
                        best_values.add(min(current_value,row.get(j+1)));
                    }
                }
            }else{
                shortest_paths.add(best_values);
                break;
            }
        }
        Collections.reverse(shortest_paths);
        long startTime = System.nanoTime();
        //List<Integer> shortest_path_index = tree_traverse.top_down_traversal_old(shortest_paths);
        List<Integer> shortest_path_index = tree_traverse.path_finder_top_down(shortest_paths);
        long endTime = System.nanoTime();
        traverse_time = (endTime - startTime);

        List<Integer> shortest_path_values = new ArrayList<>();

        for(int i = 0; i < content.size(); i++){//getting the values of the tree from the index
            shortest_path_values.add(content.get(i).get(shortest_path_index.get(i)));
        }

        startTime = System.nanoTime();
        visualisation.tree_print(content);
        //visualisation.array_print(content);
        endTime = System.nanoTime();
        print_time = (endTime - startTime);

        //System.out.println("Shortest Path Traversal (Index): " + shortest_path_index);
        //System.out.println("Shortest Path Traversal (Values): " + shortest_path_values);
        //System.out.println("Shortest Path Sum: "+ shortest_path_values.stream().mapToInt(Integer::intValue).sum());
        System.out.println("Minimum path is: " + shortest_path_values.stream().map(Object::toString).collect( Collectors.joining(" + ",""," = "))+ shortest_path_values.stream().mapToInt(Integer::intValue).sum());
    }
    */
}
