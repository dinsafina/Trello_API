package property;

public class Values {
    private static String url;
    private static String user_key;
    private static String user_token;

    public Values(String url, String user_key, String user_token) {
        this.url = url;
        this.user_key = user_key;
        this.user_token = user_token;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUser_key() {
        return user_key;
    }


    public static String getUser_token() {
        return user_token;
    }
}
