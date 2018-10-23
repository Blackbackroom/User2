package persisting;

import java.io.IOException;
import java.util.Properties;

public class Config {

    static {
        try{
            Properties properties=new Properties();
            properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            user=properties.getProperty("user");
            dbUrl=properties.getProperty("dbUrl");
            dbUser=properties.getProperty("dbUser");
            dbPassword=properties.getProperty("dbPassword");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String user;
    public static String dbUrl;
    public static String dbUser;
    public static String dbPassword;

}
