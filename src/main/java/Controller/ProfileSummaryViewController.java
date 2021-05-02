/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: Controller for ProfileSummaryView.fxml.
 */

package Controller;

import Model.ProfileSummary.Characters;
import Model.ProfileSummary.WowAccounts;
import Utilities.APIRequest.MediaSummary.MediaSummaryRequest;
import Utilities.APIRequest.ProfileSummary.ProfileSummaryRequest;
import Utilities.APIResponse.MediaSummary.MediaSummaryResponse;
import Utilities.APIResponse.ProfileSummary.ProfileSummaryResponse;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileSummaryViewController implements Initializable
{
    // instance variables.
    @FXML
    private ListView<Characters> characterListView;
    @FXML
    private ImageView characterImageView;
    @FXML
    private TextField classTextField;
    @FXML
    private TextField factionTextField;
    @FXML
    private TextField levelTextField;
    @FXML
    private TextField raceTextField;
    @FXML
    private TextField genderTextField;
    @FXML
    private TextField realmTextField;


    // create a method to populate the characterListView.
    public void populateCharacterListView() throws IOException
    {
        // Run through the request response.
        // 1.) the response from the ProfileSummary endpoint request.
        ProfileSummaryResponse response = new Gson().fromJson(ProfileSummaryRequest.profileSummaryGet(), ProfileSummaryResponse.class);

        // 2.) contains all the user's WoW accounts, linked to their Blizzard account.
        WowAccounts[] wowAccounts = response.getWowAccounts();

        // 3.) use wowAccounts[0] to get the characters, need to test with multiple accounts. - I have one so use the first.
        Characters[] characterList = wowAccounts[0].getCharacters();

        // add the character[] to the characterListView.
        characterListView.getItems().addAll(characterList);
    }// end of populateListView().


    // create a method to populate the characterImageView.
    public void populateCharacterImageView() throws IOException
    {
        // Run through the request response.
        // 1.) the response from the ProfileSummary endpoint request.
        MediaSummaryResponse response = new Gson().fromJson(MediaSummaryRequest.mediaSummaryGet(), MediaSummaryResponse.class);

        // 2.)
        String a = response.getAssets()[0].getValue();


        System.out.println("");
    }// end of populateCharacterImageView().


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        // config of scene.
        classTextField.setEditable(false);
        factionTextField.setEditable(false);
        levelTextField.setEditable(false);
        raceTextField.setEditable(false);
        genderTextField.setEditable(false);
        realmTextField.setEditable(false);

        // populate the views.
        try {
            populateCharacterListView();
            populateCharacterImageView();
        } catch (IOException e) {
            e.printStackTrace();
        }// end of try-catch.

        // depending on the character that is selected, populate respective fields.
        characterListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, characterSelected) -> {
                    classTextField.setText(characterSelected.getCharacterClass().toString());
                    factionTextField.setText(characterSelected.getCharacterFaction().toString());
                    levelTextField.setText(String.valueOf(characterSelected.getCharacterLevel()));
                    raceTextField.setText(characterSelected.getCharacterRace().toString());
                    genderTextField.setText(characterSelected.getCharacterGender().toString());
                    realmTextField.setText(characterSelected.getCharacterRealm().toString());
                });

        // this will auto-select the first item in the list. - improves user interactivity.
        characterListView.getSelectionModel().select(0);
        characterListView.getFocusModel().focus(0);
    }// end of initialize().
}// end of class.
