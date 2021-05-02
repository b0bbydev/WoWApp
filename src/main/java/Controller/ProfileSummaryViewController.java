/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: Controller for ProfileSummaryView.fxml.
 */

package Controller;

import Model.ProfileSummary.Characters;
import Model.ProfileSummary.WowAccounts;
import Utilities.APIRequest.ProfileSummaryRequest;
import Utilities.APIResponse.ProfileSummaryResponse;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileSummaryViewController implements Initializable
{
    // instance variables.
    @FXML
    private ListView<Characters> characterListView;


    // create a method to populate the characterListView.
    public void populateCharacterListView() throws IOException
    {
        // Run through the response from request.
        // 1.) the response from the ProfileSummary endpoint request.
        ProfileSummaryResponse response = new Gson().fromJson(ProfileSummaryRequest.profileSummaryGet(), ProfileSummaryResponse.class);

        // 2.) contains all the user's WoW accounts, linked to their Blizzard account.
        WowAccounts[] wowAccounts = response.getWowAccounts();

        // 3.) use wowAccounts[0] to get the characters, need to test with multiple accounts. - I have one so use the first.
        Characters[] characterList = wowAccounts[0].getCharacters();

        // add the character[] to the characterListView.
        characterListView.getItems().addAll(characterList);
    }// end of populateListView().


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // populate the characterListView.
        try {
            populateCharacterListView();
        } catch (IOException e) {
            e.printStackTrace();
        }// end of try-catch.
    }// end of initialize().
}// end of class.
