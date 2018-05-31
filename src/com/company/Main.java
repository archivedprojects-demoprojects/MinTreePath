package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        List<int[]> content = file_read("data_small.txt");
        if(content != null) {
            array_print(content);
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

}
