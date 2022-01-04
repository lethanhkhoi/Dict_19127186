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
    Random rand = new Random();
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
    public Vector<String> find(String slangwords,int type) {
        Vector<String> temp = new Vector<String>();
        if (words.get(slangwords) == null)
            return null;
        if (type == 0) { //0 la tra ra vector de show len list
            for (int i = 0; i < words.get(slangwords).size(); i++) {
                temp.add(slangwords + "-" + words.get(slangwords).get(i));
            }
        }
        else{//1 la tra ra vector<String> chua difinition
            for (int i = 0; i < words.get(slangwords).size(); i++) {
                temp.add(words.get(slangwords).get(i));
            }
        }

        return temp;
    }
    public void update_history(String slangwords){
        history.put(slangwords,words.get(slangwords));
        try {
            FileWriter file = new FileWriter("history.txt",true);
            file.write(slangwords+"\n");
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Vector<String> find_byDefinition(String difi){
        Vector<String>temp = new Vector<String>();
        for(Map.Entry<String,Vector<String>> entry:words.entrySet()){
            for(int i = 0;i<entry.getValue().size();i++){
                if(entry.getValue().get(i).contains(difi)){
                    String str = entry.getValue().get(i);
                    temp.add(entry.getKey()+"-"+str);
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
            order.add(slangword);
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
            if(words.get(slangword)==null){
                words.remove(slangword);
                order.remove(slangword);
            }
            return true;
        }
    }
    public boolean delete(String slangword){
        if(words.get(slangword)==null){
            return false;
        }else{
            words.remove(slangword);
            order.remove(slangword);
            return true;
        }
    }
    public String random_slangword(){

        int num = rand.nextInt((words.size()-1));
        return order.get(num);
    }
    public String random_difinition(){
        int num = rand.nextInt(words.size()-1);
        String temp = order.get(num);
        Vector<String> tmp = words.get(temp);

        return tmp.get(rand.nextInt(tmp.size()));
    }

    public boolean export_file(String filename){
        try {
            FileWriter fow= new FileWriter(filename);
            String str = "Slag`Meaning";
            fow.write(str);
            for(int i =1;i<order.size();i++){
                String key = order.get(i);
                for(int j =0;j<words.get(key).size();j++){
                    fow.write("\n"+key+"`"+words.get(key).get(j));
                }
            }
            fow.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public Vector<String> view_history(){
        Vector<String>temp = new Vector<String>();
        for(Map.Entry<String,Vector<String>> entry:history.entrySet()){
            for(int i = 0;i<entry.getValue().size();i++){
                String str = entry.getValue().get(i);
                temp.add(entry.getKey()+" - "+str);
            }
        }
        return temp;
    }
}
