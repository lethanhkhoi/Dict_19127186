package vn.edu.hcmus.student.sv19127186.Dictionary;
import vn.edu.hcmus.student.sv19127186.Dictionary.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Vector;

public class Main {

    public static void main(String[] args) throws IOException {
	    SlangWords dict= new SlangWords();
        Vector<String> temp = dict.find_byDefinition("Love");

        for(int i=0;i<temp.size();i++){
            System.out.println(temp.get(i));
        }
    }
}
