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

    private JPanel       contentPane;
    private HostTemplate hostGoogle;
    private HostTemplate hostYoutube;
    private HostTemplate hostAmazon;

    private final int WIDTH  = 236;
    private final int HEIGHT = 150;

    private Rectangle screenSize = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

    // Setup GUI
    public GUI()
    {

        setType(Type.UTILITY);
        setUndecorated(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds((int) screenSize.width - WIDTH, (int) screenSize.height - HEIGHT, WIDTH, HEIGHT);
        setAlwaysOnTop(true);
        setOpacity(.75f);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        // Setup first HostTemplate (Google)
        hostGoogle = new HostTemplate(Config.FIRST_URL);
        hostGoogle.setBounds(10, 11, 214, 36);
        new Thread(hostGoogle).start();
        contentPane.add(hostGoogle);

        // Setup second HostTemplate (Youtube)
        hostYoutube = new HostTemplate(Config.SECOND_URL);
        hostYoutube.setBounds(10, 58, 214, 36);
        new Thread(hostYoutube).start();
        contentPane.add(hostYoutube);

        // Setup third HostTemplate (Amazon)
        hostAmazon = new HostTemplate(Config.THIRD_URL);
        hostAmazon.setBounds(10, 105, 214, 36);
        new Thread(hostAmazon).start();
        contentPane.add(hostAmazon);

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
