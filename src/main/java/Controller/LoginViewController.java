/*
 * Name: Bobby Jonkman
 * Date: April.29.2021
 * Purpose: Controller for LoginView.fxml
 */

package Controller;

import Utilities.Config;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginViewController extends Config implements Initializable {
    // instance variables.
    @FXML
    private Button loginButton;


    // create a method to get the currentStage and close it.
    public void closeStage() {
        // get the current stage.
        Stage stage = (Stage) loginButton.getScene().getWindow();
        // close it.
        stage.close();
    }// end of closeStage().


    /**
     * This method will start the OAuth2 flow when the button is clicked.
     *
     * @param event login button being pressed.
     */
    @FXML
    void login(ActionEvent event) throws URISyntaxException, IOException {
        requestAccessToken();
    }// end of login().


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }// end of initialize().
}// end of class.