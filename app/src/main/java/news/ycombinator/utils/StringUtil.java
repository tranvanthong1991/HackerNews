package news.ycombinator.utils;

public class StringUtil {

    public static String[] split(String text, String pattern) {
        String[] result = new String[] {};
        if ((text != null) && (pattern != null)) {
            pattern = pattern.trim();

            if (pattern.length() > 0) {
                int ind = text.indexOf(pattern);
                if (ind != -1) {
                    String item = text.substring(0, ind);
                    String[] temp = split(
                            text.substring(ind + pattern.length(),
                                    text.length()), pattern);
                    result = new String[temp.length + 1];
                    result[0] = item.trim();
                    for (int i = 0; i < temp.length; i++) {
                        result[i + 1] = temp[i];
                    }
                } else {
                    result = new String[] { text };
                }
            }
        }

        return result;
    }
}
