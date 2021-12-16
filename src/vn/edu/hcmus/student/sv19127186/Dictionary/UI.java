package vn.edu.hcmus.student.sv19127186.Dictionary;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.util.Vector;

/**
 * vn.edu.hcmus.student.sv19127186.Dictionary
 * Created by 84904
 * Date 14/12/2021 - 11:48 PM
 * Description: ...
 */


public class UI extends JPanel implements ActionListener{
    private JButton add;
    private JButton delete;
    private JButton update;
    private JButton play_slangword;
    private JButton play_difinition;
    private JButton import_;
    private JButton reset_;
    private JButton search_slang;
    private JButton search_difi;
    private JButton history;
    private JTextField search;
    SlangWords dict;

    private JList list;
    private add_UI add_ui;
    private edit_UI edit_ui;
    private delete_UI delete_ui;
    UI()
    {

        dict=new SlangWords();
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(500,350));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx=0;
        gbc.gridy=0;
        add = new JButton("add");
        add.setActionCommand("ADD");
        delete = new JButton("delete");
        delete.setActionCommand("DELETE");
        update = new JButton("update");
        update.setActionCommand("UPDATE");
        play_slangword = new JButton("play game with slangword");
        play_difinition = new JButton("play game with difinition");
        import_ = new JButton("import");
        reset_ =new JButton("reset");
        reset_.setActionCommand("RESET");
        history = new JButton("History");
        history.setActionCommand("HISTORY");
        search_slang=new JButton("Search slang word");
        search_slang.setActionCommand("slang");
        search_difi=new JButton("Search difinition");
        search_difi.setActionCommand("difinition");

        JPanel chucnang = new JPanel(new GridBagLayout());

        //
        list = new JList();
        Vector<String> words=new Vector<String>();
        list.setListData(words);

        //
        gbc= new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.anchor= GridBagConstraints.EAST;
        gbc.insets = new Insets(5,5,5,5);
        chucnang.add(new JLabel("Search:"),gbc);

        search = new JTextField(12);
        gbc.gridx=1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        chucnang.add(search,gbc);

        gbc = new GridBagConstraints();
        gbc.anchor= GridBagConstraints.WEST;
        gbc.gridy=1;
        gbc.gridx=0;
        gbc.insets = new Insets(5,5,5,5);
        chucnang.add(search_slang,gbc);
        gbc.gridx++;
        search_difi.setPreferredSize(new Dimension(141, 26));
        chucnang.add(search_difi,gbc);

        gbc = new GridBagConstraints();
        gbc.anchor= GridBagConstraints.WEST;
        gbc.gridy=2;
        gbc.gridx=0;
        gbc.insets = new Insets(5,5,5,5);
        add.setPreferredSize(new Dimension(141, 26));
        chucnang.add(add,gbc);
        gbc.gridx++;

        update.setPreferredSize(new Dimension(141, 26));
        chucnang.add(update,gbc);

        gbc.gridy++;
        gbc.gridx=0;
        gbc.insets = new Insets(5,5,5,5);
        delete.setPreferredSize(new Dimension(141, 26));
        chucnang.add(delete,gbc);
        gbc.gridx++;
        import_.setPreferredSize(new Dimension(141, 26));
        import_.setActionCommand("import");
        chucnang.add(import_,gbc);

        gbc.gridy++;
        gbc.gridx=0;
        reset_.setPreferredSize(new Dimension(141, 26));
        chucnang.add(reset_,gbc);
        gbc.gridx++;
        history.setPreferredSize(new Dimension(141, 26));
        chucnang.add(history,gbc);

        JPanel footerpanel = new JPanel();
        footerpanel.setLayout(new BoxLayout(footerpanel,BoxLayout.LINE_AXIS));
        footerpanel.add(play_slangword);
        play_slangword.setActionCommand("game_slang");
        footerpanel.add(Box.createRigidArea(new Dimension(20,0)));
        footerpanel.add(play_difinition);
        play_difinition.setActionCommand("game_difi");
        JPanel temp = new JPanel(new BorderLayout());
        temp.add(footerpanel,BorderLayout.LINE_END);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(temp);



        JPanel header = new JPanel(new FlowLayout());
        JLabel name = new JLabel("Dictionary", SwingConstants.CENTER);
        name.setForeground(Color.red);
        name.setFont(header.getFont().deriveFont (20.0f));
        header.add(name);

        add(header,BorderLayout.PAGE_START);
        add(chucnang,BorderLayout.LINE_START);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(panel,BorderLayout.SOUTH);
        import_.addActionListener(this);

        search_slang.addActionListener(this);
        search_difi.addActionListener(this);
        play_slangword.addActionListener(this);
        play_difinition.addActionListener(this);
        add.addActionListener(this);
        delete.addActionListener(this);
        update.addActionListener(this);
        reset_.addActionListener(this);
        history.addActionListener(this);
    }
    public void createGUI()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setOpaque(true);
        frame.setContentPane(this);

        frame.pack();
        frame.setSize(new Dimension(700,400));

        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command == "slang") {
            String str = search.getText();
            if(str.length()==0)
                return;
            Vector<String> temp = dict.find(str);
            if(temp!=null)
            {
                list.setListData(temp);
                dict.update_history(str);
            }
            else{
                JOptionPane.showMessageDialog(null,"Cannot find this slang word","Error Message", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(command=="difinition"){
            String str = search.getText();
            if(str.length()==0)
                return;
            Vector<String> temp = dict.find_byDefinition(str);
            list.setListData(temp);
        }
        if(command =="RESET"){
            try {
                dict.import_file("slang.txt");
                dict.export_file("data.txt");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else if(command=="ADD"){
            add_ui= new add_UI(dict);

        }
        else if(command=="DELETE"){
            delete_ui = new delete_UI(dict);
        }
        else if(command == "UPDATE"){
            edit_ui = new edit_UI(dict);
        }
        else if (command=="HISTORY"){
            Vector<String>temp = dict.view_history();
            if(temp!=null)
                list.setListData(temp);
        }
    }
}