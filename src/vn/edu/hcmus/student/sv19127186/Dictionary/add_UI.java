package vn.edu.hcmus.student.sv19127186.Dictionary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import vn.edu.hcmus.student.sv19127186.Dictionary.*;
/**
 * vn.edu.hcmus.student.sv19127186.Dictionary
 * Created by 84904
 * Date 15/12/2021 - 10:35 PM
 * Description: ...
 */
public class add_UI extends JFrame implements ActionListener {
    private JTextField slangword;
    private JTextField meanings;

    private JButton okbtn;
    private JButton cancelbtn;
    SlangWords dict;
    add_UI(SlangWords temp){
        dict=temp;
        setDefaultLookAndFeelDecorated(true);
        setTitle("ADD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(300,200));
        JPanel panel = new JPanel(new BorderLayout());

        slangword=new JTextField(15);
        meanings = new JTextField(15);
        okbtn = new JButton("OK");
        cancelbtn = new JButton("Cancel");
        okbtn.setActionCommand("OK");
        cancelbtn.setActionCommand("Cancel");

        JPanel panel1 = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor= GridBagConstraints.WEST;
        gbc.insets = new Insets(5,5,5,5);
        panel1.add(new JLabel("Slang Word: "),gbc);
        gbc.gridy++;
        panel1.add(new JLabel("Meanings: "),gbc);

        gbc.gridx++;
        gbc.gridy=0;
        gbc.anchor=GridBagConstraints.WEST;
        panel1.add(slangword,gbc);
        gbc.gridy++;
        panel1.add(meanings,gbc);


        //footer
        JPanel footerpanel = new JPanel();
        footerpanel.setLayout(new BoxLayout(footerpanel,BoxLayout.LINE_AXIS));
        footerpanel.add(okbtn);
        footerpanel.add(Box.createRigidArea(new Dimension(20,0)));
        footerpanel.add(cancelbtn);
        JPanel tmp = new JPanel(new BorderLayout());
        tmp.add(footerpanel,BorderLayout.LINE_END);

        JPanel panel2 = new JPanel(new FlowLayout());
        panel2.add(tmp);

        panel.add(panel1,BorderLayout.CENTER);
        panel.add(panel2,BorderLayout.SOUTH);
        add(panel);

        okbtn.addActionListener(this);
        cancelbtn.addActionListener(this);

        this.pack();
        setSize(new Dimension(300,200));
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command=e.getActionCommand();
        if(command=="OK"){
            String slangword_= this.slangword.getText();
            if(slangword.getText().length()==0){
                JOptionPane.showMessageDialog(null,"Please your slangword");

            }
            String mean_ = this.meanings.getText();
            if(meanings.getText().length()==0){
                JOptionPane.showMessageDialog(null,"Please input your meanings");
                return;
            }
            Vector<String> temp = this.dict.find(slangword_);
            dict.add_newWord(slangword_,mean_);
            dict.export_file("data.txt",slangword_,mean_);
            this.hide();

        }
        if(command=="Cancel"){
            this.hide();
            return;
        }
    }
}
