package de.codersgen.available_checker;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class HostTemplate extends JFrame
{

    private JPanel contentPane;

    public HostTemplate()
    {
        setUndecorated(true);
        setType(Type.UTILITY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 214, 36);
        contentPane = new JPanel();
        contentPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblHostname = new JLabel("Hostname");
        lblHostname.setBounds(34, 11, 170, 14);
        contentPane.add(lblHostname);
        
        JLabel lblRechable = new JLabel("");
        lblRechable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        lblRechable.setOpaque(true);
        lblRechable.setBackground(Color.RED);
        lblRechable.setBounds(10, 11, 14, 14);
        contentPane.add(lblRechable);
    }

}
