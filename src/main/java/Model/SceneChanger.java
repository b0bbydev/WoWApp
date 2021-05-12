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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class SceneChanger {
    /**
     * Change the scene.
     *
     * @param event    the event
     * @param viewName the view name
     * @param title    the title
     * @throws IOException the io exception
     */
    public static void changeScene(ActionEvent event, String viewName, String title) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(new Object() {
        }.getClass().getResource(viewName)));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Assets/main.css");
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.getIcons().add(new Image("Assets/wowicon.png"));
        stage.setTitle(title);
        stage.show();
    }// end of changeScene().

    /**
     * This method is used a back button that directs to HomeView.fxml. It assumes there is a TextField on the page which is used to get the current Stage.
     *
     * @param textField TextField.
     * @throws IOException IOException.
     */
    public static void changeToHomeView(TextField textField) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(new Object() {
        }.getClass().getResource("../View/HomeView.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add("Assets/main.css");
        Stage stage = (Stage) textField.getScene().getWindow();
        stage.setScene(scene);
        stage.getIcons().add(new Image("Assets/wowicon.png"));
        stage.setTitle("HomeView");
        stage.show();
    }// end of changeToHomeView().

    /**
     * This method will close the current Stage.
     *
     * @param button the current stage can be found from an element on the Stage - In this case a button.
     */
    public static void closeStage(Button button) {
        // get the current stage.
        Stage stage = (Stage) button.getScene().getWindow();
        // close it.
        stage.close();
    }// end of closeStage().
}// end of class.
