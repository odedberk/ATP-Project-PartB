package Server;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class Configurations {
    private static Properties prop;
    private static void initProp(){
        try (InputStream input = Configurations.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null)
                return;
            prop = new Properties();
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public static String getProperty(String name){
        if (prop==null)
            initProp();
        return prop.getProperty(name);
    }

    public static void main(String[] args) {
        System.out.println(getProperty("test"));
    }
}