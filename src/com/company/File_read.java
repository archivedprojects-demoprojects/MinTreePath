package com.company;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class File_read {
    private List<List<Integer>> tree_read = new ArrayList<>();

    protected List<List<Integer>> file_read_buffer(String file_name){
        String line;
        int[] line_content;
        List<List<Integer>> content = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file_name);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //Using Lambda Expressions
                line_content = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
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

    protected List<List<Integer>> file_read_decl(String file_name){
        tree_read = new ArrayList<>(); //Instantiating tree output
        try{
            List<String> lines = Files.readAllLines(Paths.get(file_name), StandardCharsets.UTF_8);
            return tree_creator(lines);
            //return tree_creator_opt(lines);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }catch (IOException io){
            System.err.println(io);
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
        for(int i = 0; i < rows.size(); i++){
            int[] line_content = Arrays.stream(rows.get(i).split(" ")).mapToInt(Integer::parseInt).toArray();
            tree_read.add(IntStream.of(line_content).boxed().collect(Collectors.toList()));
        }
        return tree_read;
    }

}
