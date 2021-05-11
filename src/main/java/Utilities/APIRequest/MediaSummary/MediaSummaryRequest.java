/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: This class will make the request to Blizzard API CharacterMediaSummary endpoint.
 */

package Utilities.APIRequest.MediaSummary;

import Utilities.APIRequest.Request;
import Utilities.Config;
import Utilities.DBUtility;
import com.google.gson.JsonObject;
import com.microsoft.alm.oauth2.useragent.AuthorizationException;

import java.io.IOException;
import java.net.URISyntaxException;

public class MediaSummaryRequest extends Config
{
    // create a method to make the request to endpoint.
    public static JsonObject mediaSummaryGet(String characterName)
    {
        return Request.makeGetRequest("https://us.api.blizzard.com/profile/wow/character/zuljin/" + characterName + "/character-media?namespace=profile-us&locale=en_US&access_token=" + DBUtility.getAccessToken());
    }// end of mediaSummaryGet().
}// end of class.
