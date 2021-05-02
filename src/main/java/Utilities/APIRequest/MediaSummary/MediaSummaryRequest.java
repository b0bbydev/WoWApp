/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: This class will make the request to Blizzard API CharacterMediaSummary endpoint.
 */

package Utilities.APIRequest.MediaSummary;

import Utilities.APIRequest.Request;
import com.google.gson.JsonObject;

import java.io.IOException;

public class MediaSummaryRequest
{
    // instance variables.


    // create a method to make the request to endpoint.
    public static JsonObject mediaSummaryGet() throws IOException
    {
        return Request.makeGetRequest("https://us.api.blizzard.com/profile/wow/character/zuljin/bobdapally/character-media?namespace=profile-us&locale=en_US&access_token=USiEPT1rILM2Ubg7bZVEFMFX1SdFxg5S4s");
    }// end of mediaSummaryGet().
}// end of class.
