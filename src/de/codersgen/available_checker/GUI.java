package de.codersgen.available_checker;

import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GUI extends JFrame implements Runnable
{
    private boolean isRunning = true;

    private JPanel contentPane;

    private int windowWidth  = 236;
    private int windowHeight = 150;

    private Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

    // Setup GUI
    public GUI()
    {
        setType(Type.UTILITY);
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setAlwaysOnTop(true);
        setOpacity(.75f);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        // Set window height correct
        windowHeight = Config.MARGIN + (Config.URL_LIST.length * Config.TEMPLATE_HEIGHT);
        setBounds((int) screenSize.width - windowWidth, (int) screenSize.height - windowHeight, windowWidth, windowHeight);
        // Add all hosts that a found in the config file
        for (int i = 0; i < Config.URL_LIST.length; i++)
        {
            HostTemplate temp = new HostTemplate(Config.URL_LIST[i][0], Config.URL_LIST[i][1]);
            temp.setBounds(10, Config.MARGIN + (i * Config.TEMPLATE_HEIGHT), 214, 36);
            new Thread(temp).start();
            contentPane.add(temp);
        }

        setContentPane(contentPane);

        // Start the "Thread"
        new Thread(this).start();
    }

    // Check if mouse if hover the GUI
    public boolean isHover()
    {
        if (MouseInfo.getPointerInfo().getLocation().getX() >= getLocation().getX()
                && MouseInfo.getPointerInfo().getLocation().getY() >= getLocation().getY())
            return true;
        return false;
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
            if (isHover())
                setVisible(false);
            else
                setVisible(true);
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
