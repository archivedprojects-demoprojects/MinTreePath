package com.company;

import java.util.List;

public class Main {
    private static final Sum_of_path sum_of_path = new Sum_of_path();
    private static final File_read file_read = new File_read();

    public static void main(String[] args) {
        List<List<Integer>> content = file_read.file_read_buffer(args[0]); // data_small ; data_big ; data_test ; data_gen;
        if(content != null) {
            sum_of_path.result(content);
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