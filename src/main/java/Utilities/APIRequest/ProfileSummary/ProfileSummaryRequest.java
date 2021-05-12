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

public class ProfileSummaryRequest extends Config {
    /**
     * This method will make a request to the ProfileSummary endpoint Blizzard provides.
     *
     * @return The response of the request.
     */
    public static JsonObject profileSummaryGet() {
        return Request.makeGetRequest("https://us.api.blizzard.com/profile/user/wow?namespace=profile-us&locale=en_US&access_token=" + DBUtility.getAccessToken());
    }// end of profileSummaryGet().
}// end of class.
