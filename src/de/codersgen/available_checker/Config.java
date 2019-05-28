package de.codersgen.available_checker;

import java.util.ArrayList;

public class Config
{
    private static ArrayList<String> testURL = new ArrayList<String>();
    
    public Config()
    {
        testURL.add("www.google.com");
        testURL.add("www.youtube.com");
        testURL.add("www.amazon.de");
    }
    
    public static ArrayList<String> getTestList()
    {
        return testURL;
    }
}
