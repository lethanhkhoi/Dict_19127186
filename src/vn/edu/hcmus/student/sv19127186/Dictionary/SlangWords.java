package vn.edu.hcmus.student.sv19127186.Dictionary;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * vn.edu.hcmus.student.sv19127186.Dictionary
 * Created by 84904
 * Date 13/12/2021 - 9:59 PM
 * Description: ...
 */
public class SlangWords {
    Map<String, Vector<String>> words;

    SlangWords() {
        try {
            this.import_file("data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void import_file(String filename) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filename));
        words = new HashMap<String, Vector<String>>();
        String line =br.readLine();
        line=br.readLine();
        System.out.println(line);
        while(line!=null) {
            String str[] = line.split("`");
            String key = str[0];
            System.out.println(key);
            if (words == null || words.get(key) == null) {
                Vector<String> values = new Vector<String>();
                values.add(str[1]);
                words.put(key, values);

            } else {
                if (words.get(key) != null) {
                    words.get(key).add(str[1]);
                }
            }
            line = br.readLine();
        }
    }
    public Vector<String> find(String slangwords){
        Vector<String>temp = new Vector<String>();
        temp=words.get(slangwords);
        return temp;
    }
}
