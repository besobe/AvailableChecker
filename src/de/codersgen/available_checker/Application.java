package de.codersgen.available_checker;

import java.awt.EventQueue;

import de.codersgen.available_checker.ui.GUI;

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
