package database;

import database.exceptions.DbExeption;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

    private static Connection connection = null;


    public static Connection getConnection(){
        if (connection == null){
            try {
                Properties  properties = loadProperties();
                String url = properties.getProperty("dburl");
                connection = DriverManager.getConnection(url,properties);
            }
            catch (SQLException | IOException e){
                throw  new DbExeption(e.getMessage());
            }
        }
        return connection;
    }

    public static  void  closeConnection(){
        if (connection != null){
            try {
                connection.close();
            }
            catch (SQLException e){
                throw new DbExeption(e.getMessage());
            }
        }
    }


    private static Properties loadProperties() throws IOException {
        try (FileInputStream fs = new FileInputStream("db.properties")){
            Properties props = new Properties();
            props.load(fs);
            return props;
        }catch (IOException e){
            throw new DbExeption(e.getMessage());
        }
    }
}
