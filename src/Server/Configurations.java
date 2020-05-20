package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    public static void setProperty(String name, String val) {
        initProp();
        prop.setProperty(name,val);
    }

    public static String getProperty(String name){
        return prop.getProperty(name);
    }
}