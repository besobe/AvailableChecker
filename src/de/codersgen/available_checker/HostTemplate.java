package de.codersgen.available_checker;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import java.net.InetAddress;

import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class HostTemplate extends JPanel implements Runnable
{
    // Declare variables
    private String  sURL;
    private String  sHostname;
    private JLabel  lblHostname;
    private JLabel  lblReachable;
    private boolean isRunning = true;

    public HostTemplate(String url, String hostname)
    {
        // Setup URL and hostname
        sURL = url;
        sHostname = hostname;

        // Setup Default Values
        setBounds(100, 100, 214, 36);
        setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        setLayout(null);

        // Set Hostname Default
        lblHostname = new JLabel("Hostname");
        lblHostname.setBounds(34, 11, 170, 14);
        add(lblHostname);

        // Set Reachable Default
        lblReachable = new JLabel("");
        lblReachable.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        lblReachable.setOpaque(true);
        lblReachable.setBackground(Color.RED);
        lblReachable.setBounds(10, 11, 14, 14);
        add(lblReachable);
    }

    // Reset hostname and reachable
    public void reset()
    {
        lblHostname.setText("");
        lblReachable.setBackground(Color.GRAY);
    }

    // Set rechable by boolean
    public void setReachable(boolean isReachable)
    {
        if (isReachable)
            lblReachable.setBackground(Color.GREEN);
        else
            lblReachable.setBackground(Color.RED);
    }

    // Set reachable by boolean and ping
    public void setReachable(boolean isReachable, long ping)
    {
        if (isReachable)
            if (ping > Config.BAD_PING)
                lblReachable.setBackground(Color.RED);
            else if (ping > Config.MID_PING)
                lblReachable.setBackground(Color.YELLOW);
            else
                lblReachable.setBackground(Color.GREEN);
        else
            lblReachable.setBackground(Color.RED);
    }

    // Set hostname
    public void setHostname(String hostname)
    {
        lblHostname.setText(hostname);
    }

    // Set hostname and ping
    public void setHostname(String hostname, long ping)
    {
        lblHostname.setText(hostname + " (" + ping + "ms)");
    }

    // Kill the thread
    public void kill()
    {
        isRunning = false;
    }
    
    // Thread run method
    @Override
    public void run()
    {
        while (isRunning)
        {
            checkConnection();
            try
            {
                Thread.sleep(Config.CHECK_INTERVAL);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // Check connection and ping
    public void checkConnection()
    {
        if (sURL == null || sHostname == null)
        {
            kill();
            return;
        }

        try
        {
            InetAddress address = InetAddress.getByName(sURL);
            long ping = System.currentTimeMillis();
            if (address.isReachable(Config.TIMEOUT))
            {
                ping = (System.currentTimeMillis() - ping);
                setReachable(true, ping);
                setHostname(sHostname, ping);
            }
            else
            {
                setReachable(false);
                setHostname(sHostname);
            }
        }
        catch (Exception e)
        {
            setReachable(false);
            setHostname(sHostname);
        }
    }
}
