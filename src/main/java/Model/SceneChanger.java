/*
 * Name: Bobby Jonkman
 * Date: April.29.2021
 * Purpose: This allows changing from javafx scene to scene.
 */

package Model;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneChanger
{
    /**
     * Change the scene.
     *
     * @param event    the event
     * @param viewName the view name
     * @param title    the title
     * @throws IOException the io exception
     */
    public static void changeScene(ActionEvent event, String viewName, String title) throws IOException
    {
        Parent root = FXMLLoader.load(Objects.requireNonNull(new Object(){}.getClass().getResource(viewName)));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Assets/main.css");
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }// end of changeScene().
}// end of class.
