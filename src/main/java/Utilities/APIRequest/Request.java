package Utilities.APIRequest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Request
{
    // instance variables.
    private static String content;


    // create a method to make a GET request to an endpoint.
    public static JsonObject makeGetRequest(String endpoint)
    {
        // Using HttpClient to make the POST to exchange the auth code for the token.
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(endpoint);

        try
        {
            // Execute the request.
            HttpResponse response = client.execute(get);

            // Get the content as a String.
            content = EntityUtils.toString(response.getEntity());
        } catch(IOException e)
        {
            System.out.println("Request Error.");
        }// end of try-catch.

        // parse the json String.
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(content);
        return element.getAsJsonObject();
    }// end of makeRequest().


    // create a method to make a POST request to an endpoint.
    public static JsonObject makePostRequest(String endpoint)
    {
        // Using HttpClient to make the POST to exchange the auth code for the token.
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(endpoint);

        try
        {
            // Execute the request.
            HttpResponse response = client.execute(post);

            // Get the content as a String.
            content = EntityUtils.toString(response.getEntity());
        } catch(IOException e)
        {
            System.out.println("Request Error.");
        }// end of try-catch.

        // parse the json String.
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(content);
        return element.getAsJsonObject();
    }// end of makePostRequest().
}// end of class.
