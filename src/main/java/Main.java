/*
 * Name: Bobby Jonkman
 * Date: April.29.2021
 * Purpose: World of Warcraft account viewer.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }// end of main.

    @Override
    public void start(Stage stage) throws IOException
    {
        //Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("View/LoginView.fxml")));
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("View/HomeView.fxml")));
        Scene scene = new Scene(root);
        root.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Assets/main.css")).toExternalForm());
        stage.setScene(scene);
        stage.setTitle("World of Warcraft Application");
        stage.show();
    }// end of start().
}// end of class.
