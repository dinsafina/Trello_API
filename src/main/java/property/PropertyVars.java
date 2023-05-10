package property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyVars {
    public static final String PATH_TO_PROPERTIES = "variables.properties";

public static Values getProperty(){
    String url;
    String key;
    String token;
    Properties prop = new Properties();

    try (InputStream in = PropertyVars.class.getClassLoader().getResourceAsStream(PATH_TO_PROPERTIES)) {
        prop.load(in);
    } catch (IOException ex) {
        System.out.println("Ошибка в программе: файл " + PATH_TO_PROPERTIES + " не обнаружено");
    }
    url = prop.getProperty("trello_url");
    key = prop.getProperty("key");
    token = prop.getProperty("token");
    return new Values(url, key, token);
}

}
