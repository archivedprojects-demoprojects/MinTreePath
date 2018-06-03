package com.company;

import java.util.List;

public class Main {
    private static final Sum_of_path sum_of_path = new Sum_of_path();
    private static final File_read file_read = new File_read();
    private static final Tree_generator tree_generator = new Tree_generator();

    /*
    * StackOverflowError when trying to run files containing more than 2.75K of rows
    * */
    public static void main(String[] args) {
        tree_generator.generator(2500);
        long startTime = System.nanoTime();
        List<List<Integer>> content = file_read.file_read_buffer("data_test.txt"); // data_small ; data_big ; data_test ; data_gen
        long endTime = System.nanoTime();
        long read_duration = (endTime - startTime);

        if(content != null) {
            startTime = System.nanoTime();
            sum_of_path.result(content);
            endTime = System.nanoTime();
            long path_duration = (endTime - startTime) - (sum_of_path.traverse_time + sum_of_path.print_time);

            //Testing timings on different text files
            System.out.println("File Read Buf   - Duration: "+read_duration/1000000+"ms / "+ read_duration +"ns");
            System.out.println("Sum Calc        - Duration: "+path_duration/1000000+"ms / "+ path_duration +"ns");
            System.out.println("Traverse        - Duration: "+sum_of_path.traverse_time/1000000+"ms / "+ sum_of_path.traverse_time+"ns");
            System.out.println("Print           - Duration: "+sum_of_path.print_time/1000000+"ms / "+ sum_of_path.print_time +"ns");
            long total_time = read_duration+path_duration+sum_of_path.traverse_time+sum_of_path.print_time;
            System.out.println("Total Time      - Duration: "+ total_time/1000000 + "ms / " + total_time + "ns");
        }
    }
}

/*
 * Data Analysis (Time taken):
 * file_read
 *   data_big    : 239ms
 *   data_small  : 30ms
 *   data_test   : 18ms
 *
 * sum_calculation_time
 *   data_big    : 152ms
 *   data_small  : 19ms
 *   data_test   : 15ms
 *                                  Old methods
 * top_down_sum                         top_down_traversal_old
 *   data_big    : 2ms                    data_big    : 20ms
 *   data_small  : 0ms                    data_small  : 0ms
 *   data_test   : 0ms                    data_test   : 0ms
 *
 * tree_print                           array_print
 *   data_big    : 1544ms                 data_big    : 3434ms
 *   data_small  : 6ms                    data_small  : 18ms
 *   data_test   : 1ms                    data_test   : 9ms
 *
 *   Total
 *   data_big    : 1936ms
 *   data_small  : 42ms
 *   data_test   : 36ms
 */