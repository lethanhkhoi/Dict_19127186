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
class add_nofication extends JFrame implements ActionListener{
    private SlangWords dict;
    private JButton duplicate;
    private JButton overwrite;
    private JList list;
    private String slangword_;
    private String mean_;

    add_nofication(SlangWords temp,String slangword,String mean){
        dict=temp;
        slangword_=slangword;
        mean_=mean;
        setDefaultLookAndFeelDecorated(true);
        setTitle("Notification");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setMinimumSize(new Dimension(300,200));
        //center
        list= new JList();
        JPanel panel = new JPanel(new BorderLayout());
        Vector<String> str = dict.find(slangword);
        list.setListData(str);

        //header
        JPanel header = new JPanel(new FlowLayout());
        JLabel name = new JLabel("This word has existed already", SwingConstants.CENTER);
        name.setForeground(Color.red);
        name.setFont(header.getFont().deriveFont (20.0f));
        header.add(name);

        //footer
        duplicate = new JButton("NEW");
        overwrite = new JButton("OVERWRITE");
        duplicate.setActionCommand("NEW");
        overwrite.setActionCommand("OVERWRITE");

        JPanel footerpanel = new JPanel();
        footerpanel.setLayout(new BoxLayout(footerpanel,BoxLayout.LINE_AXIS));
        footerpanel.add(duplicate);
        footerpanel.add(Box.createRigidArea(new Dimension(20,0)));
        footerpanel.add(overwrite);
        JPanel tmp = new JPanel(new BorderLayout());
        tmp.add(footerpanel,BorderLayout.LINE_END);
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.add(tmp);

        panel.add(header,BorderLayout.PAGE_START);
        panel.add(new JScrollPane(list),BorderLayout.CENTER);
        panel.add(panel1,BorderLayout.SOUTH);
        add(panel);

        this.pack();
        setSize(new Dimension(300,200));
        setVisible(true);
        duplicate.addActionListener(this);
        overwrite.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command =e.getActionCommand();
        if(command=="NEW"){
            dict.add_newWord(slangword_,mean_);
            JOptionPane.showMessageDialog(null,"Add successfully");
            dict.export_file("data.txt");
            this.hide();
        }
        else if(command=="OVERWRITE"){
            int index = list.getSelectedIndex();
            dict.edit_Word(slangword_,index,mean_);
            JOptionPane.showMessageDialog(null,"Add successfully");
            dict.export_file("data.txt");
            this.hide();

        }
    }
}
public class add_UI extends JFrame implements ActionListener {
    private JTextField slangword;
    private JTextField meanings;

    private JButton okbtn;
    private JButton cancelbtn;
    SlangWords dict;
    private add_nofication extra;
    add_UI(SlangWords temp){
        dict=temp;
        setDefaultLookAndFeelDecorated(true);
        setTitle("ADD");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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
            Vector<String>temp = dict.find(slangword_);
            if(temp!=null)
                extra = new add_nofication(dict,slangword_,mean_);
            else{
                dict.add_newWord(slangword_,mean_);
                JOptionPane.showMessageDialog(null,"Add successfully");
                dict.export_file("data.txt");
            }

            this.hide();

        }
        if(command=="Cancel"){
            this.hide();
            return;
        }
    }
}
