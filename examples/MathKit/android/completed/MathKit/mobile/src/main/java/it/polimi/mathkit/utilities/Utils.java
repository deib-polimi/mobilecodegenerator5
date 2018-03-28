package it.polimi.mathkit.utilities;

/**
 * Created by Max on 23/03/2018.
 */

public class Utils {
    public static String truncateResult(String input) {
        if(!input.contains("."))
            return input;
        String[] splittedInput = input.split("\\.");
        if(splittedInput[1].length() <= 2)
            return input;
        return splittedInput[0] + "." + splittedInput[1].substring(0, 2);
    }
}
