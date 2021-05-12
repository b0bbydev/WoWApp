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

public class MediaSummaryRequest extends Config
{
    /**
     * This method will make a request to the MediaSummary endpoint Blizzard provides.
     *
     * @param characterName The desired character to search.
     * @return The response of the request.
     */
    public static JsonObject mediaSummaryGet(String characterName)
    {
        return Request.makeGetRequest("https://us.api.blizzard.com/profile/wow/character/zuljin/" + characterName + "/character-media?namespace=profile-us&locale=en_US&access_token=" + DBUtility.getAccessToken());
    }// end of mediaSummaryGet().
}// end of class.
