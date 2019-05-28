package de.codersgen.available_checker;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class HostTemplate extends JPanel
{
    private JLabel lblHostname;
    private JLabel lblReachable;

    public HostTemplate()
    {
        setBounds(100, 100, 214, 36);
        setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        setLayout(null);
        
        lblHostname = new JLabel("Hostname");
        lblHostname.setBounds(34, 11, 170, 14);
        add(lblHostname);
        
        lblReachable = new JLabel("");
        lblReachable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        lblReachable.setOpaque(true);
        lblReachable.setBackground(Color.RED);
        lblReachable.setBounds(10, 11, 14, 14);
        add(lblReachable);
    }
    
    public void reset()
    {
        lblHostname.setText("");
        lblReachable.setBackground(Color.GRAY);
    }
    
    public void setReachable(boolean isReachable)
    {
        if (isReachable)
            lblReachable.setBackground(Color.GREEN);
        else
            lblReachable.setBackground(Color.RED);
    }
    
    public void setHostname(String hostname)
    {
        lblHostname.setText(hostname);
    }
    
    public void setHostname(String hostname, String ping)
    {
        lblHostname.setText(hostname + " (" + ping + "ms)");
    }
}
