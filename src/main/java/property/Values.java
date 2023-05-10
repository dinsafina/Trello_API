package property;

public class Values {
    private String url;
    private String user_key;
    private String user_token;

    public Values(String url, String user_key, String user_token) {
        this.url = url;
        this.user_key = user_key;
        this.user_token = user_token;
    }

    public String getUrl() {
        return url;
    }

    public String getUser_key() {
        return user_key;
    }


    public String getUser_token() {
        return user_token;
    }
}
