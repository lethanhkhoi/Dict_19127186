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
class delete_nofication extends JFrame implements ActionListener{
    private SlangWords dict;
    private JButton delete_one;
    private JButton delete_all;
    private JList list;
    private String slangword_;


    delete_nofication(SlangWords temp,String slangword){
        dict=temp;
        slangword_=slangword;

        setDefaultLookAndFeelDecorated(true);
        setTitle("Notification");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setMinimumSize(new Dimension(500,200));
        //center
        list= new JList();
        JPanel panel = new JPanel(new BorderLayout());
        Vector<String> str = dict.find(slangword);
        list.setListData(str);

        //header
        JPanel header = new JPanel(new FlowLayout());
        JLabel name = new JLabel("Which one do you want to delete or you can delete all", SwingConstants.CENTER);
        name.setForeground(Color.red);
        name.setFont(header.getFont().deriveFont (20.0f));
        header.add(name);

        //footer
        delete_one = new JButton("DELETE ONE");
        delete_all = new JButton("DELETE ALL");
        delete_one.setActionCommand("ONE");
        delete_all.setActionCommand("ALL");

        JPanel footerpanel = new JPanel();
        footerpanel.setLayout(new BoxLayout(footerpanel,BoxLayout.LINE_AXIS));
        footerpanel.add(delete_one);
        footerpanel.add(Box.createRigidArea(new Dimension(20,0)));
        footerpanel.add(delete_all);
        JPanel tmp = new JPanel(new BorderLayout());
        tmp.add(footerpanel,BorderLayout.LINE_END);
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.add(tmp);

        panel.add(header,BorderLayout.PAGE_START);
        panel.add(new JScrollPane(list),BorderLayout.CENTER);
        panel.add(panel1,BorderLayout.SOUTH);
        add(panel);

        this.pack();
        setSize(new Dimension(500,200));
        setVisible(true);
        delete_one.addActionListener(this);
        delete_all.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command =e.getActionCommand();
        if(command=="ALL"){
            boolean check = dict.delete(slangword_);
            if (check == true){
                JOptionPane.showMessageDialog(null,"DELETE successfully");
                dict.export_file("data.txt");
                this.hide();
            }
            else{
                JOptionPane.showMessageDialog(null,"Cannot find this slang word","Error Message", JOptionPane.ERROR_MESSAGE);
            }

        }
        else if(command=="ONE"){
            int index = list.getSelectedIndex();
            dict.delete(slangword_,index);
            JOptionPane.showMessageDialog(null,"DELETE successfully");
            dict.export_file("data.txt");
            this.hide();

        }
    }
}
public class delete_UI extends JFrame implements ActionListener {
    private JTextField slangword;

    private JButton okbtn;
    private JButton cancelbtn;
    SlangWords dict;
    private delete_nofication extra;
    delete_UI(SlangWords temp){
        dict=temp;
        setDefaultLookAndFeelDecorated(true);
        setTitle("DELETE");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setMinimumSize(new Dimension(300,200));
        JPanel panel = new JPanel(new BorderLayout());


        slangword=new JTextField(15);
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


        gbc.gridx++;
        gbc.gridy=0;
        gbc.anchor=GridBagConstraints.WEST;
        panel1.add(slangword,gbc);


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
                JOptionPane.showMessageDialog(null,"Please input your slangword");
            }
            Vector<String>temp = dict.find(slangword_);
            if(temp!=null) {
                extra = new delete_nofication(dict, slangword_);
                this.hide();
            }
            else{
                JOptionPane.showMessageDialog(null,"Cannot find this slang word","Error Message", JOptionPane.ERROR_MESSAGE);
            }

        }
        if(command=="Cancel"){
            this.hide();
            return;
        }
    }
}
