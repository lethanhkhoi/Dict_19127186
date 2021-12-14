package vn.edu.hcmus.student.sv19127186.Dictionary;

import java.io.*;
import java.util.*;

/**
 * vn.edu.hcmus.student.sv19127186.Dictionary
 * Created by 84904
 * Date 13/12/2021 - 9:59 PM
 * Description: ...
 */
public class SlangWords {
    Map<String, Vector<String>> words;
    Vector<String> order;
    Map<String,Vector<String>> history;
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
        history=new HashMap<String,Vector<String>>();
        order = new Vector<String>();
        String line =br.readLine();
        line=br.readLine();

        while(line!=null) {
            String str[] = line.split("`");
            String key = str[0];
            if (words == null || words.get(key) == null) {
                Vector<String> values = new Vector<String>();
                values.add(str[1]);
                words.put(key, values);
                order.add(key);

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
        for(int i =0;i<words.get(slangwords).size();i++)
        {
            temp.add(words.get(slangwords).get(i));
        }
        history.put(slangwords,temp);
        return temp;
    }
    public Vector<String> find_byDefinition(String difi){
        Vector<String>temp = new Vector<String>();
        for(Map.Entry<String,Vector<String>> entry:words.entrySet()){
            for(int i = 0;i<entry.getValue().size();i++){
                if(entry.getValue().get(i).contains(difi)){
                    temp.add(entry.getValue().get(i));
                }
            }
        }
        return temp;
    }
    public void add_newWord(String slangword,String value){

        if(words.get(slangword)==null){
            Vector<String>values = new Vector<String>();
            values.add(value);
            words.put(slangword,values);
        }
        else{
            words.get(slangword).add(value);
        }
    }
    public boolean edit_Word(String slangword,int index,String new_value){
        if(words.get(slangword)==null){
            return false;
        }
        else{
            words.get(slangword).set(index,new_value);
            return true;
        }
    }
    public boolean delete(String slangword,int index){
        if(words.get(slangword)==null){
            return false;
        }
        else{
            words.get(slangword).remove(index);
            if(words.get(slangword).size()==0){
                words.remove(slangword);
            }
            return true;
        }
    }
    public boolean delete(String slangword){
        if(words.get(slangword)==null){
            return false;
        }else{
            words.remove(slangword);
            return true;
        }
    }
    public String random_slangword(){
        Random rand = new Random();
        int num = rand.nextInt(100);
        return order.get(num);
    }
}
