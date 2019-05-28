package de.codersgen.available_checker;

import java.awt.EventQueue;

public class Application
{
    public static void main(String args[])
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Config config = new Config();
                    GUI frame = new GUI();
                    frame.setVisible(true);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}
