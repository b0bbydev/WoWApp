/*
 * Name: Bobby Jonkman
 * Date: April.29.2021
 * Purpose: Controller for LoginView.fxml
 */

package Controller;

import Model.SceneChanger;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.alm.oauth2.useragent.AuthorizationException;
import com.microsoft.alm.oauth2.useragent.AuthorizationResponse;
import com.microsoft.alm.oauth2.useragent.UserAgent;
import com.microsoft.alm.oauth2.useragent.UserAgentImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class LoginViewController implements Initializable
{
    // instance variables.
    @FXML
    private Button loginButton;

    // These are pulled from gradle.properties.
    String blizzardDomain;
    String clientId;
    String clientSecret;
    String redirectUri;
    String scope;
    String grantType;
    boolean loggedIn = false;


    /**
     * Loads our config info from the app.properties file
     * @throws IOException IOException
     */
    private void loadAppProperties() throws IOException
    {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("app.properties");
        Properties appProps = new Properties();
        appProps.load(inputStream);
        blizzardDomain = appProps.getProperty("blizzardDomain");
        clientId = appProps.getProperty("blizzardClientId");
        clientSecret = appProps.getProperty("blizzardClientSecret");
        redirectUri = appProps.getProperty("redirectUri");
        scope = appProps.getProperty("scope");
        grantType = appProps.getProperty("grantType");
    }// end of loadAppProperties().


    /**
     * Build the authorization request URL
     *
     * @return the authorization URL.
     * @throws URISyntaxException URISyntaxException
     * @throws MalformedURLException MalformedURLException
     */
    private URI buildAuthorizationUrl() throws URISyntaxException, MalformedURLException
    {
        URIBuilder builder = new URIBuilder();

        builder.setScheme("https");
        builder.setHost(blizzardDomain);
        builder.setPath("/oauth/authorize");
        builder.addParameter("client_id", clientId);
        builder.addParameter("redirect_uri", redirectUri);
        builder.addParameter("response_type", "code");
        //builder.addParameter("state", "this is a state");
        builder.addParameter("scope", scope);

        URL url = builder.build().toURL();

        return url.toURI();
    }// end of buildAuthorizationUrl().


    /**
     * Requests an authorization code from the auth server
     * @return the authorization code.
     * @throws MalformedURLException MalformedURLException
     * @throws URISyntaxException URISyntaxException
     */
    private String getAuthorizationCode() throws IOException, URISyntaxException
    {
        // load properties from app.properties.
        loadAppProperties();

        // Generate the auth endpoint URI to request the authCode.
        URI authorizationEndpoint = buildAuthorizationUrl();

        final URI redirectUri = new URI(this.redirectUri);

        AuthorizationResponse authorizationResponse = null;

        // Create the user agent and make the call to the auth endpoint.
        final UserAgent userAgent = new UserAgentImpl();

        try {
            authorizationResponse = userAgent.requestAuthorizationCode(authorizationEndpoint, redirectUri);
        } catch (Exception e)
        {
            System.out.println("Authorization Response most likely null.");
            if(authorizationResponse == null)
            {
                return null;
            } else {
                return authorizationResponse.getCode();
            }
        }// end of try-catch.

        return authorizationResponse.getCode();
    }// end of requestAuthCode().


    /**
     * Given an authorization code, calls the auth server to request a token
     * @param authCode authorization code.
     * @return the accessToken.
     * @throws URISyntaxException URISyntaxException
     * @throws IOException IOException
     */
    private String getAccessToken(String authCode) throws URISyntaxException, IOException
    {
        // The token request URL.
        final String tokenUrl = "https://" + blizzardDomain + "/oauth/token";

        // The original redirect URL.
        final URI redirectUri = new URI(this.redirectUri);

        // Using HttpClient to make the POST to exchange the auth code for the token.
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(tokenUrl);

        // Adding the POST params to the request.
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("grant_type", grantType));
        urlParameters.add(new BasicNameValuePair("code", authCode));
        urlParameters.add(new BasicNameValuePair("redirect_uri", redirectUri.toString()));
        urlParameters.add(new BasicNameValuePair("client_id", clientId));
        urlParameters.add(new BasicNameValuePair("client_secret", clientSecret));
        urlParameters.add(new BasicNameValuePair("scope", scope));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        // Execute the request.
        HttpResponse response = client.execute(post);

        // Get the content as a String.
        String content = EntityUtils.toString(response.getEntity());

        // parse the json String.
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(content);
        JsonObject jsonObject = element.getAsJsonObject();

        // try to assign accessToken variable for null check.
        String accessToken = null;
        try {
            accessToken = jsonObject.get("access_token").getAsString();
        } catch(Exception e)
        {
            System.out.println("AccessToken most likely null.");
        }// end of try-catch.

        if(accessToken == null)
        {
            loggedIn = false;
        } else {
            loggedIn = true;
        }// end of if-else.

        return accessToken;
    }// end of getTokenForCode().


    @FXML
    void accessToken(ActionEvent event) throws AuthorizationException, IOException, URISyntaxException
    {
        // call getAccessToken method when the button is clicked.
        getAccessToken(getAuthorizationCode());

        // check if user is logged in before switching to next scene.
        if(loggedIn)
        {
            SceneChanger.changeScene(event, "../View/Homeview.fxml", "HomeView");
        }// end of if.
    }// end of accessToken().


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }// end of initialize().
}// end of class.
