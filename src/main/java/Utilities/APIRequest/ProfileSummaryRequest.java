/*
 * Name: Bobby Jonkman
 * Date: May.2.2021
 * Purpose: This class will make the request to Blizzard API AccountProfileSummary endpoint.
 */

package Utilities.APIRequest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class ProfileSummaryRequest
{
    // instance variables.


    // create a method to make the request to endpoint.
    public static JsonObject profileSummaryGet() throws IOException
    {
        // The token request URL.
        final String endpoint = "https://us.api.blizzard.com/profile/user/wow?namespace=profile-us&locale=en_US&access_token=USFqO3YcZhqrlA22ZHqwjAiFVFtERhrllq";

        // Using HttpClient to make the POST to exchange the auth code for the token.
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(endpoint);

        // Execute the request.
        HttpResponse response = client.execute(get);

        // Get the content as a String.
        String content = EntityUtils.toString(response.getEntity());

        // parse the json String.
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(content);

        return element.getAsJsonObject();
    }// end of profileSummaryGet().
}// end of class.
