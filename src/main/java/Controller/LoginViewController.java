/*
 * Name: Bobby Jonkman
 * Date: April.29.2021
 * Purpose: Controller for LoginView.fxml
 */

package Controller;

import Model.SceneChanger;
import Utilities.Config;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController extends Config implements Initializable {
    // instance variables.
    @FXML
    private Button loginButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem closeMenuItem;

    /**
     * This method will close the current Stage.
     * @param button the current stage can be found from an element on the Stage - In this case a button.
     */
    public static void closeStage(Button button) {
        // get the current stage.
        Stage stage = (Stage) button.getScene().getWindow();
        // close it.
        stage.close();
    }// end of closeStage().

    /**
     * This event is used in the menuItem, and will close the current Stage.
     */
    EventHandler<ActionEvent> closeEvent = event -> closeStage(loginButton);

    /**
     * This method will start the OAuth2 flow when the button is clicked.
     *
     * @param event login button being pressed.
     */
    @FXML
    void login(ActionEvent event) throws IOException {

        // close the Stage when the login button is clicked.
        closeStage(loginButton);

        // use the exception throwing to check if the user is logged in.
        boolean loggedIn = false;
        try {
            requestAccessToken();
            loggedIn = true;
        } catch(Exception e)
        {
            // if there is an error thrown, the login process should fail and be reset back to the initial LoginView.
            loggedIn = false;
            SceneChanger.changeScene(event, "../View/LoginView.fxml", "LoginView");
        }// end of try-catch.

        // if the user is logged in, change the scene to HomeView.fxml.
        if(loggedIn)
        {
            SceneChanger.changeScene(event, "../View/HomeView.fxml", "HomeView");
        }// end of if.
    }// end of login().

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        closeMenuItem.setOnAction(closeEvent);
    }// end of initialize().
}// end of class.