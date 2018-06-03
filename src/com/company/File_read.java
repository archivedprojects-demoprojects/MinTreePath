package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class File_read {
    protected List<List<Integer>> file_read_buffer(String file_name){
        String line;
        int[] line_content;
        List<List<Integer>> content = new ArrayList<>();
        try {
            FileReader file_reader = new FileReader(file_name);
            BufferedReader buffered_reader = new BufferedReader(file_reader);

            while((line = buffered_reader.readLine()) != null) {
                //Using Lambda Expressions to split the line based on white spaces
                line_content = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                content.add(IntStream.of(line_content).boxed().collect(Collectors.toList()));
            }

            buffered_reader.close();
            if(content.size() == 0 ){
                System.out.println("File is empty");
            }
            return content;
        }catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + file_name + "'");
            return null;
        }catch(IOException ex) {
            System.out.println("Error reading file '" + file_name + "'");
            return null;
        }
    }

    /*
    private List<List<Integer>> tree_read = new ArrayList<>();

    protected List<List<Integer>> file_read_decl(String file_name){
        tree_read = new ArrayList<>(); //Instantiating tree output
        try{
            List<String> lines = Files.readAllLines(Paths.get(file_name), StandardCharsets.UTF_8);
            return tree_creator(lines);
            //return tree_creator_opt(lines);
        } catch (IOException io){
            io.printStackTrace();
        }
        return null;
    }

    private List<List<Integer>> tree_creator(List<String> rows){
        if(rows.size() > 0){
            int [] current_row = Arrays.stream(rows.get(0).split(" ")).mapToInt(Integer::parseInt).toArray();
            tree_read.add(IntStream.of(current_row).boxed().collect(Collectors.toList()));
            rows.remove(0);
            tree_creator(rows);
        }
        return tree_read;
    }

    private List<List<Integer>> tree_creator_decl(List<String> rows){
        for (String row : rows) {
            int[] line_content = Arrays.stream(row.split(" ")).mapToInt(Integer::parseInt).toArray();
            tree_read.add(IntStream.of(line_content).boxed().collect(Collectors.toList()));
        }
        return tree_read;
    }
    */
}
