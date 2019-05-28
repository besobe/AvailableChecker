package de.codersgen.available_checker;

public class Config
{
    // Setup Config variables and values
    public final static int TIMEOUT         = 5000;
    public final static int CHECK_INTERVAL  = 3000;
    public final static int MID_PING        = 100;
    public final static int BAD_PING        = 200;
    public final static int MARGIN          = 11;
    public final static int TEMPLATE_HEIGHT = 47;

    public final static String[][] URL_LIST =
    {
            { "www.capgemini.com", "Capgemini.com" },
            { "www.sogeti.com", "Sogeti.com" },
            { "www.google.com", "Google.com" },
            { "www.github.com", "GitHub.com" }, };
}
