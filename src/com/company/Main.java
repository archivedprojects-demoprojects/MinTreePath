package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        List<int[]> content = file_read("data_test.txt");
        if(content != null) {
            bottom_up_approach_naive(content);
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("Duration: "+duration/1000000+"ms");
    }

    private static List<int[]> file_read(String file_name){
        String line;
        int[] line_content;
        List<int[]> content = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file_name);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //TODO - check how to optimise loop below
            while((line = bufferedReader.readLine()) != null) {
                //Using Lambda Expressions
                line_content = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                //line_content = Integer.parseInt(line.split(" ")); - Old approach using Strings
                content.add(line_content);
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
    private static void array_print(List<int[]> content){
        for (int[] rows : content) {
            for (int values : rows) {
                System.out.print(values + " ");
            }
            System.out.println();
        }
    }

    private static void bottom_up_approach_naive(List<int[]> content){
        List<int[]> bottom = new ArrayList<>(content);
        List<int[]> shortest_paths = new ArrayList<>();

        Collections.reverse(bottom);
        array_print(bottom);
        array_print(content);

        //from the leave we can go parent having the same index or index - 1
        //ie if we are at leaf 4, I can go to the following parent notes: 3,4
        for (int i =0; i <bottom.size(); i++) {
            /*            if(i == 0){
                shortest_paths.add(bottom.get(i));
            }else */
            if(i != bottom.size()-1) {
                for (int j = 0; j < bottom.get(i).length; j++) {
                    int current_value = bottom.get(i)[j];
                    if (j == 0) {
                        System.out.println("Edge L:" + current_value);
                    } else if (j == bottom.get(i).length - 1) { //first and last cases (far edges - have only 1 parent
                        System.out.println("Edge R:" + current_value);
                    } else {
                        System.out.println("Leaf:" + current_value);
                    }
                }
            }else{
                int current_value = bottom.get(i)[0];
                System.out.println("Parent: "+ current_value);
            }
            System.out.println();
        }
    }
}
