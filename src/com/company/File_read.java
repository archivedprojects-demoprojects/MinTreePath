package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class File_read {
    protected List<List<Integer>> file_read(String file_name){
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
}
