package news.ycombinator.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidationUtil {

    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches())
            return true;
        return false;
    }

    public static int getInt(String input){
        String regex = "\\d+";
        if(input.matches(regex))
            return Integer.parseInt(input);
        return 0;
    }

}
