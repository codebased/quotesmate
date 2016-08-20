package helpers;

/**
 * Created by codebased on 30/07/16.
 */
public class StringUtil {
    public static String capitalFirstLetter(String str) {
        String[] strArray = str.split(" ");
        StringBuilder builder = new StringBuilder();
        for (String s : strArray) {
            String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
            builder.append(cap + " ");
        }

        return builder.toString();
    }

    public static boolean isNullOrWhiteSpace(String str) {
        return (str == null || str.trim().length() == 0);
    }

    public static boolean isNotNullAndWhiteSpace(String str) {
        return !isNullOrWhiteSpace(str);
    }
}
