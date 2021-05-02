/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: This class will make the request to Blizzard API AccountProfileSummary endpoint.
 */

package Utilities.APIRequest.ProfileSummary;

import Utilities.APIRequest.Request;
import com.google.gson.JsonObject;

import java.io.IOException;

public class ProfileSummaryRequest
{
    // instance variables.


    // create a method to make the request to endpoint.
    public static JsonObject profileSummaryGet() throws IOException
    {
        return Request.makeGetRequest("https://us.api.blizzard.com/profile/user/wow?namespace=profile-us&locale=en_US&access_token=USFqO3YcZhqrlA22ZHqwjAiFVFtERhrllq");
    }// end of profileSummaryGet().
}// end of class.
