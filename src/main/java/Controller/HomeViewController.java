/*
 * Name: Bobby Jonkman
 * Date: April.29.2021
 * Purpose: Controller for HomeView.fxml
 */

package Controller;

import Model.SceneChanger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomeViewController
{
    // instance variables.
    @FXML
    private Button profileSummaryButton;


    @FXML
    void profileSummary(ActionEvent event) throws IOException
    {
        SceneChanger.changeScene(event, "../View/ProfileSummaryView.fxml", "ProfileSummaryView");
    }// end of profileSummary().
}// end of class.
