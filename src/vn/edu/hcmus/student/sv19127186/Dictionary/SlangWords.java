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
    Map<String, HashMap<Integer,String>> words;

    SlangWords() {
        try {
            this.import_file("data.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void import_file(String filename) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filename));
        words = new HashMap<String, HashMap<Integer,String>>();
        String line =br.readLine();
        line=br.readLine();

        while(line!=null) {
            int i =0;
            String str[] = line.split("`");
            String key = str[0];
            if (words == null || words.get(key) == null) {
                HashMap<Integer,String> values = new HashMap<Integer,String>();
                values.put(i,str[1]);
                words.put(key, values);

            } else {
                if (words.get(key) != null) {
                    System.out.println(words.get(key));
                    i=words.get(key).size();System.out.println("i: "+i);
                    HashMap<Integer,String> values = words.get(key);
                    values.put(i,str[1]);
                    System.out.println("i=0: "+values.get(i-1));
                    System.out.println("i=1: "+values.get(i));
                    words.replace(key, values);
                }
            }
            line = br.readLine();
        }
    }
    public Vector<String> find(String slangwords){
        Vector<String>temp = new Vector<String>();
        for(int i =0;i<words.get(slangwords).size();i++)
        {
            temp.add(words.get(slangwords).get(i));
        }
        return temp;
    }
    public Vector<String> find_byDefinition(String difi){
        Vector<String>temp = new Vector<String>();


        return temp;
    }
}
