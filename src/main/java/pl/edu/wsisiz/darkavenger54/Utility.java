package pl.edu.wsisiz.darkavenger54;

public class Utility
{
    public static boolean tryParseIntAndCheckIntegerClosed(String input, int min, int max)
    {
        try
        {
            int value = Integer.parseInt(input);
            if(value >= min && value <= max)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (NumberFormatException e)
        {
            return false;
        }
    }
}
