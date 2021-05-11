/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: This class will make the request to Blizzard API AccountProfileSummary endpoint.
 */

package Utilities.APIRequest.ProfileSummary;

import Utilities.APIRequest.Request;
import Utilities.Config;
import Utilities.DBUtility;
import com.google.gson.JsonObject;
import com.microsoft.alm.oauth2.useragent.AuthorizationException;

import java.io.IOException;
import java.net.URISyntaxException;

public class ProfileSummaryRequest extends Config
{
    // create a method to make the request to endpoint.
    public static JsonObject profileSummaryGet() {
        return Request.makeGetRequest("https://us.api.blizzard.com/profile/user/wow?namespace=profile-us&locale=en_US&access_token=" + DBUtility.getAccessToken());
    }// end of profileSummaryGet().
}// end of class.
