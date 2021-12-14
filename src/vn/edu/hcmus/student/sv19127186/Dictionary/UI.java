package vn.edu.hcmus.student.sv19127186.Dictionary;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton sort_GPA;
    private JButton sort_id;
    private JButton import_;
    private JButton export_;
    private JButton save_;


    UI()
    {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(750,350));
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx=0;
        gbc.gridy=0;
        add = new JButton("add");
        add.setActionCommand("ADD");
        delete = new JButton("delete");
        delete.setActionCommand("DELETE");
        update = new JButton("update");
        update.setActionCommand("UPDATE");
        sort_GPA = new JButton("sort_GPA");
        sort_id = new JButton("sort_id");
        import_ = new JButton("import");
        export_ = new JButton("export");
        save_ =new JButton("save");
        save_.setActionCommand("SAVE");

        JPanel chucnang = new JPanel(new GridBagLayout());

        //

        gbc = new GridBagConstraints();
        gbc.anchor= GridBagConstraints.WEST;
        gbc.gridy=7;
        gbc.gridx=0;
        gbc.insets = new Insets(5,5,5,5);
        chucnang.add(add,gbc);
        gbc.gridx++;

        JPanel tmp = new JPanel();
        tmp.setLayout(new BoxLayout(tmp,BoxLayout.LINE_AXIS));
        tmp.add(update);
        tmp.add(Box.createRigidArea(new Dimension(20,0)));
        tmp.add(delete);
        chucnang.add(tmp,gbc);
        gbc.gridy++;
        gbc.gridx=0;
        gbc.insets = new Insets(5,5,5,5);
        chucnang.add(save_,gbc);
        gbc.gridx++;


        JPanel tmp1 = new JPanel();
        tmp1.setLayout(new BoxLayout(tmp1,BoxLayout.LINE_AXIS));
        tmp1.add(import_);
        import_.setActionCommand("import");
        tmp1.add(Box.createRigidArea(new Dimension(20,0)));
        tmp1.add(export_);
        chucnang.add(tmp1,gbc);
        export_.setActionCommand("export");

        JPanel footerpanel = new JPanel();
        footerpanel.setLayout(new BoxLayout(footerpanel,BoxLayout.LINE_AXIS));
        footerpanel.add(sort_GPA);
        sort_GPA.setActionCommand("gpa");
        footerpanel.add(Box.createRigidArea(new Dimension(20,0)));
        footerpanel.add(sort_id);
        sort_id.setActionCommand("id");
        JPanel temp = new JPanel(new BorderLayout());
        temp.add(footerpanel,BorderLayout.LINE_END);

        JPanel panel = new JPanel(new FlowLayout());
        panel.add(temp);

        add(chucnang,BorderLayout.LINE_START);
        add(panel,BorderLayout.SOUTH);
        import_.addActionListener(this);
        export_.addActionListener(this);
        sort_GPA.addActionListener(this);
        sort_id.addActionListener(this);
        add.addActionListener(this);
        delete.addActionListener(this);
        update.addActionListener(this);
        save_.addActionListener(this);
    }
    public void createGUI()
    {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Dictionary");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setOpaque(true);
        frame.setContentPane(this);

        frame.pack();
        frame.setSize(new Dimension(780,400));

        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
    }
}