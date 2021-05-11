/*
 * Name: Bobby Jonkman
 * Date: April.29.2021
 * Purpose: This class interacts with the database for accessToken.
 */

package Utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBUtility {
    // instance variables.
    private static String accessToken;
    private static String user;
    private static String pass;

    /**
     * Loads our config info from the app.properties file
     */
    private static void loadAppProperties() {
        InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("app.properties");
        Properties appProps = new Properties();

        // try-catch in case theres a null error when loading data from app.properties.
        try {
            appProps.load(inputStream);
        } catch(IOException e) {
            System.out.println("app.properties load error...");
        }// end of try-catch.

        user = appProps.getProperty("user");
        pass = appProps.getProperty("pass");
    }// end of loadAppProperties().

    // create a method to get the accessToken from the database.
    public static String getAccessToken() {
        // load data from app.properties.
        loadAppProperties();

        try {
            // database connection.
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/wowdb", user, pass);

            // create a Statement and execute it.
            Statement statement = conn.createStatement();
            String selectQuery = "SELECT accessToken FROM credentials";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // if the resultSet is empty.
            if(!resultSet.next()) {
                System.out.println("ResultSet is empty...");
            } else {
                // else assign the accessToken variable to the result.
                do {
                    accessToken = resultSet.getString("accessToken");
                } while(resultSet.next());
            }// end of if-else.

            // close connections.
            statement.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }// end of try-catch.

        return accessToken;
    }// end of getAccessToken().
}// end of class.
