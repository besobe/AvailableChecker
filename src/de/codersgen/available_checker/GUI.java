package de.codersgen.available_checker;

import java.net.InetAddress;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame implements Runnable
{

    private JPanel       contentPane;
    private HostTemplate hostGoogle;
    private HostTemplate hostYoutube;
    private HostTemplate hostAmazon;

    /*
     * Create the frame.
     */
    public GUI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        setContentPane(contentPane);

        hostGoogle = new HostTemplate();
        hostGoogle.setBounds(10, 11, 214, 36);
        contentPane.add(hostGoogle);

        hostYoutube = new HostTemplate();
        hostYoutube.setBounds(10, 58, 214, 36);
        contentPane.add(hostYoutube);

        hostAmazon = new HostTemplate();
        hostAmazon.setBounds(10, 105, 214, 36);
        contentPane.add(hostAmazon);
        new Thread(this).start();
    }

    public void check()
    {
        System.out.println("CHECK");
        Config.getTestList().forEach((host) -> isValid(host));
    }

    public void isValid(String host)
    {
        try
        {
            InetAddress address = InetAddress.getByName(host);
            long ping = System.currentTimeMillis();
            if (address.isReachable(10 * 3000))
            {
                ping = (System.currentTimeMillis() - ping);
                if (host.equalsIgnoreCase(Config.getTestList().get(0)))
                {
                    hostGoogle.setReachable(true);
                    hostGoogle.setHostname(host, String.valueOf(ping));
                }
                if (host.equalsIgnoreCase(Config.getTestList().get(1)))
                {
                    hostYoutube.setReachable(true);
                    hostYoutube.setHostname(host, String.valueOf(ping));
                }
                if (host.equalsIgnoreCase(Config.getTestList().get(2)))
                {
                    hostAmazon.setReachable(true);
                    hostAmazon.setHostname(host, String.valueOf(ping));
                }
            }
            else
            {
                if (host.equalsIgnoreCase(Config.getTestList().get(0)))
                {
                    hostGoogle.setReachable(false);
                    hostGoogle.setHostname(host);
                }
                if (host.equalsIgnoreCase(Config.getTestList().get(1)))
                {
                    hostYoutube.setReachable(false);
                    hostYoutube.setHostname(host);
                }
                if (host.equalsIgnoreCase(Config.getTestList().get(2)))
                {
                    hostAmazon.setReachable(false);
                    hostAmazon.setHostname(host);
                }
            }
        }
        catch (Exception e)
        {
            if (host.equalsIgnoreCase(Config.getTestList().get(0)))
            {
                hostGoogle.setReachable(false);
                hostGoogle.setHostname(host);
            }
            if (host.equalsIgnoreCase(Config.getTestList().get(1)))
            {
                hostYoutube.setReachable(false);
                hostYoutube.setHostname(host);
            }
            if (host.equalsIgnoreCase(Config.getTestList().get(2)))
            {
                hostAmazon.setReachable(false);
                hostAmazon.setHostname(host);
            }
        }
    }

    @Override
    public void run()
    {
        boolean isRunning = true;
        while (isRunning)
        {
            check();
            try
            {
                Thread.sleep(3 * 1000);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
