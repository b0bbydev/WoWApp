/*
 * Name: Bobby Jonkman
 * Date: April.29.2021
 * Purpose: Controller for HomeView.fxml
 */

package Controller;

import Model.SceneChanger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static Controller.LoginViewController.closeStage;

public class HomeViewController implements Initializable {
    // instance variables.
    @FXML
    private Button profileSummaryButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private MenuItem closeMenuItem;

    /**
     * This event is used in the menuItem, and will close the current Stage.
     */
    EventHandler<ActionEvent> closeEvent = event -> closeStage(profileSummaryButton);

    /**
     * This method triggers when the button is clicked, and will change the scene to profileSummaryView.
     *
     * @param event ActionEvent
     * @throws IOException IOException
     */
    @FXML
    void changeToProfileSummary(ActionEvent event) throws IOException {
        SceneChanger.changeScene(event, "../View/ProfileSummaryView.fxml", "ProfileSummaryView");
    }// end of profileSummary().

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        closeMenuItem.setOnAction(closeEvent);
    }// end of initialize().
}// end of class.
