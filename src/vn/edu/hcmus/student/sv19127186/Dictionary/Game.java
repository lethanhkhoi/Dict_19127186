package vn.edu.hcmus.student.sv19127186.Dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;

/**
 * vn.edu.hcmus.student.sv19127186.Dictionary
 * Created by 84904
 * Date 16/12/2021 - 11:25 PM
 * Description: ...
 */
public class Game extends JFrame implements ActionListener {
    private JLabel question;
    private JButton ans_A;
    private JButton ans_B;
    private JButton ans_C;
    private JButton ans_D;
    private int type;

    private SlangWords dict;
    Game(SlangWords temp){
        dict = temp;
        setDefaultLookAndFeelDecorated(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("Game");
        setMinimumSize(new Dimension(400,200));
        JPanel panel = new JPanel(new BorderLayout());
        //
        question = new JLabel("Question:");
        ans_A = new JButton();

        ans_B = new JButton();
        ans_C = new JButton();
        ans_D = new JButton();

        //
        ans_A.setPreferredSize(new Dimension(141, 26));
        ans_D.setPreferredSize(new Dimension(141, 26));
        ans_C.setPreferredSize(new Dimension(141, 26));
        ans_B.setPreferredSize(new Dimension(141, 26));


        //
        JPanel header = new JPanel();
        header.setLayout(new FlowLayout());
        header.add(question);

        //
        JPanel content = new JPanel();
        content.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.gridx = 0;
        gbc.gridy=0;

        content.add(ans_A,gbc);
        gbc.gridx++;
        content.add(ans_B,gbc);
        gbc.gridx=0;
        gbc.gridy++;
        content.add(ans_C,gbc);
        gbc.gridx++;
        content.add(ans_D,gbc);

        add(header,BorderLayout.PAGE_START);
        add(content,BorderLayout.CENTER);



        this.pack();
        setSize(new Dimension(400,200));
        setVisible(true);
        ans_D.addActionListener(this);
        ans_C.addActionListener(this);
        ans_B.addActionListener(this);
        ans_A.addActionListener(this);
    }

    public void run_difi(){
        type = 0;//game theo difinition
        String temp = dict.random_difinition();

        String ques = "Chose the slag word of this difinition: "+temp;
        question.setText(ques);

        Vector<String> ans = new Vector<String>();
        Vector<String> correct = dict.find_byDefinition(temp);
        String[] correct_ = correct.get(0).split("-");

        Random ran = new Random();
        int index = ran.nextInt(correct.size());

        ans.add(correct_[0]);
        for(int i =0;i<3;i++){
            ans.add(dict.random_slangword());
        }
        int tmp = ran.nextInt(4);
        temp = ans.get(0);
        String swap = ans.get(tmp);
        ans.set(0,swap);
        ans.set(tmp,temp);
        ans_A.setText(ans.get(0));
        ans_B.setText(ans.get(1));
        ans_C.setText(ans.get(2));
        ans_D.setText(ans.get(3));

        if(tmp==0){
            ans_A.setActionCommand("CORRECT");
            ans_B.setActionCommand("B");
            ans_C.setActionCommand("C");
            ans_D.setActionCommand("D");
        }
        else if(tmp ==1){
            ans_B.setActionCommand("CORRECT");
            ans_A.setActionCommand("A");
            ans_C.setActionCommand("C");
            ans_D.setActionCommand("D");
        }
        else if (tmp ==2){
            ans_C.setActionCommand("CORRECT");
            ans_A.setActionCommand("A");
            ans_B.setActionCommand("B");
            ans_D.setActionCommand("D");
        }
        else if(tmp ==3){
            ans_D.setActionCommand("CORRECT");
            ans_A.setActionCommand("A");
            ans_B.setActionCommand("B");
            ans_C.setActionCommand("C");
        }

    }

    public void run_slang(){
        type =1; //1 la game theo slang word
        String temp = dict.random_slangword();
        System.out.println(temp);
        String ques = "Chose the difinition of this word: "+temp;
        question.setText(ques);

        Vector<String> ans = new Vector<String>();
        Vector<String> correct = dict.find(temp,1);
        System.out.println(correct);
        Random ran = new Random();
        int index = ran.nextInt(correct.size());
        System.out.println(index);
        ans.add(correct.get(index));
        for(int i =0;i<3;i++){
            ans.add(dict.random_difinition());
        }
        int tmp = ran.nextInt(4);
        temp = ans.get(0);
        String swap = ans.get(tmp);
        ans.set(0,swap);
        ans.set(tmp,temp);
        ans_A.setText(ans.get(0));
        ans_B.setText(ans.get(1));
        ans_C.setText(ans.get(2));
        ans_D.setText(ans.get(3));
        System.out.println(tmp);
        if(tmp==0){
            ans_A.setActionCommand("CORRECT");
            ans_B.setActionCommand("B");
            ans_C.setActionCommand("C");
            ans_D.setActionCommand("D");
        }
        else if(tmp ==1){
            ans_B.setActionCommand("CORRECT");
            ans_A.setActionCommand("A");
            ans_C.setActionCommand("C");
            ans_D.setActionCommand("D");
        }
        else if (tmp ==2){
            ans_C.setActionCommand("CORRECT");
            ans_A.setActionCommand("A");
            ans_B.setActionCommand("B");
            ans_D.setActionCommand("D");
        }
        else if(tmp ==3){
            ans_D.setActionCommand("CORRECT");
            ans_A.setActionCommand("A");
            ans_B.setActionCommand("B");
            ans_C.setActionCommand("C");
        }

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command =e.getActionCommand();
        if(command =="A" ){
            JOptionPane.showMessageDialog(null,"Wrong answer","Error Message", JOptionPane.ERROR_MESSAGE);
        }
        else if (command=="B"||command=="C"||command=="D"){
            JOptionPane.showMessageDialog(null,"Wrong answer","Error Message", JOptionPane.ERROR_MESSAGE);
        }
        else if (command =="CORRECT"){
            if(type==1)
                this.run_slang();
            else
                this.run_difi();
        }
    }

}
