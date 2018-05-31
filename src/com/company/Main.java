package com.company;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> content = file_read("data_small.txt");
        System.out.println(content);
    }

    private static List<String> file_read(String file_name){
        String line;
        List<String> content = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(file_name);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
                content.add(line);
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
