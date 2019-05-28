package de.codersgen.available_checker;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class GUI extends JFrame implements Runnable
{

    private JPanel    contentPane;
    private JTextArea textArea;

    /*
     * Create the frame.
     */
    public GUI()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setBounds(10, 11, 414, 239);
        contentPane.add(textArea);
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
                textArea.setText(host + " is reachable (" + ping + "ms)\n" + textArea.getText());
                System.out.println(host + " is reachable (" + ping + "ms)");
            }
            else
            {
                textArea.setText(host + " is not reachable (timeout)\n" + textArea.getText());
                System.out.println(host + " is not reachable (timeout)");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
                Thread.sleep(300);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            textArea.setText("");
        }
    }
}
