package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Tree_generator {
    /*Class and method used for testing purposes only*/
    public void generator(int rows) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("data_gen.txt");
            bw = new BufferedWriter(fw);

            Random r = new Random();
            int Low = 1;
            int High = 100;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j <= i; j++) {
                    //shortest_path_values.stream().map(Object::toString).collect( Collectors.joining(" + ",""," = "))
                    int random_number = r.nextInt(High-Low) + Low;
                    if(j == i){
                        bw.write(Integer.toString(random_number));
                    }else{
                        bw.write(Integer.toString(random_number));
                        bw.write(' ');
                    }
                }
                bw.newLine();
            }
            System.out.println("Tree Generated");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }
}
