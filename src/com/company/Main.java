package com.company;

import java.util.List;

public class Main {
    /*
     * Data Analysis (Time taken):
     * file_read
     *   data_big    : 296ms
     *   data_small  : 26ms
     *   data_test   : 26ms
     *
     * bottom_up_calc_top_down_traverse
     *   data_big    : 31324ms
     *   data_small  : 61ms
     *   data_test   : 26ms
     */

    public static void main(String[] args) {
        Sum_of_path sum_of_path = new Sum_of_path();
        File_read file_read = new File_read();

        long startTime = System.nanoTime();
        List<List<Integer>> content = file_read.file_read("data_test.txt");
        //List<List<Integer>> content = file_read("data_small.txt");
        //List<List<Integer>> content = file_read("data_big.txt");
        long endTime = System.nanoTime();
        long read_duration = (endTime - startTime);
        if(content != null) {
            startTime = System.nanoTime();

            sum_of_path.bottom_up_calc_top_down_traverse(content);
            endTime = System.nanoTime();
            long path_duration = (endTime - startTime);
            //Testing timings on different text files

            System.out.println("File Read   - Duration: "+read_duration/1000000+"ms");
            System.out.println("Path        - Duration: "+path_duration/1000000+"ms");
        }
    }
}
