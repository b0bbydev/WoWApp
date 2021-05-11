/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: Controller for ProfileSummaryView.fxml.
 */

package Controller;

import Model.ProfileSummary.Characters;
import Utilities.APIResponse.MediaSummary.MediaSummaryResponse;
import Utilities.APIResponse.ProfileSummary.ProfileSummaryResponse;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static Utilities.APIRequest.MediaSummary.MediaSummaryRequest.mediaSummaryGet;
import static Utilities.APIRequest.ProfileSummary.ProfileSummaryRequest.profileSummaryGet;

public class ProfileSummaryViewController implements Initializable {
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
    public void populateCharacterListView() {
        ProfileSummaryResponse response = new Gson().fromJson(profileSummaryGet(), ProfileSummaryResponse.class);

        // add the character[] to the characterListView.
        characterListView.getItems().addAll(response.getWowAccounts()[0].getCharacters());
    }// end of populateListView().


    // create a method to get the character image from Blizzard API.
    public String getCharacterImage(Characters character) {
        // the response from MediaSummaryRequest.
        MediaSummaryResponse response = new Gson().fromJson(mediaSummaryGet(character.getCharacterName().toLowerCase()), MediaSummaryResponse.class);

        // if the character does not have a valid image url.
        if(response.getCode() == 404) {
            // some random error image, will change later.
            return "https://simpleandseasonal.com/wp-content/uploads/2018/02/Crockpot-Express-E6-Error-Code.png";
        } else {
            // return the third element is Assets array, it's the largest image of your character Blizzard offers.
            return response.getAssets()[3].getValue();
        }// end of if-else().
    }// end of getCharacterImage().


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // config of scene.
        classTextField.setEditable(false);
        factionTextField.setEditable(false);
        levelTextField.setEditable(false);
        raceTextField.setEditable(false);
        genderTextField.setEditable(false);
        realmTextField.setEditable(false);
        try {
            populateCharacterListView();
        } catch(Exception e) {
            e.printStackTrace();
        }

        // depending on the character that is selected, populate respective fields.
        characterListView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldValue, characterSelected) -> {
                    classTextField.setText(characterSelected.getCharacterClass().toString());
                    factionTextField.setText(characterSelected.getCharacterFaction().toString());
                    levelTextField.setText(String.valueOf(characterSelected.getCharacterLevel()));
                    raceTextField.setText(characterSelected.getCharacterRace().toString());
                    genderTextField.setText(characterSelected.getCharacterGender().toString());
                    realmTextField.setText(characterSelected.getCharacterRealm().toString());
                    //characterImageView.setImage(new Image(getCharacterImage(characterSelected)));
                });

        // this will auto-select the first item in the list. - improves user interactivity.
        characterListView.getSelectionModel().select(0);
        characterListView.getFocusModel().focus(0);
    }// end of initialize().
}// end of class.
