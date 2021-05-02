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

public class Request
{
    // instance variables.


    // create a method to make a request to an endpoint.
    public static JsonObject makeGetRequest(String endpoint) throws IOException
    {
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
    }// end of makeRequest().
}// end of class.
