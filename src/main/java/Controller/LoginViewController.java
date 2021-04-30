package Controller;

import com.microsoft.alm.oauth2.useragent.AuthorizationException;
import com.microsoft.alm.oauth2.useragent.AuthorizationResponse;
import com.microsoft.alm.oauth2.useragent.UserAgent;
import com.microsoft.alm.oauth2.useragent.UserAgentImpl;
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

    // These are pulled from gradle.properties
    String blizzardDomain;
    String clientId;
    String clientSecret;
    String redirectUri;
    String scope;
    String grantType;

    /**
     * Loads our config info from the app.properties file
     * @throws IOException IOException
     */
    public void loadProperties() throws IOException
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
    }

    /**
     * Build the authorization request URL
     *
     * @return the authorization URL.
     * @throws URISyntaxException URISyntaxException
     * @throws MalformedURLException MalformedURLException
     */
    public URI getAuthorizationEndpointUri() throws URISyntaxException, MalformedURLException
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
    }// end of getAuthorizationEndpointUri().

    /**
     * Requests an authorization code from the auth server
     * @return the authorization code.
     * @throws MalformedURLException MalformedURLException
     * @throws URISyntaxException URISyntaxException
     * @throws AuthorizationException AuthorizationException
     */
    public String requestAuthCode() throws IOException, URISyntaxException, AuthorizationException
    {
        // load properties from app.properties.
        loadProperties();

        // Generate the auth endpoint URI to request the auth code
        URI authorizationEndpoint = getAuthorizationEndpointUri();

        System.out.print("Authorization Endpoint URI: ");
        System.out.println(authorizationEndpoint.toString());

        final URI redirectUri = new URI(this.redirectUri);

        // Create the user agent and make the call to the auth endpoint
        final UserAgent userAgent = new UserAgentImpl();

        final AuthorizationResponse authorizationResponse = userAgent.requestAuthorizationCode(authorizationEndpoint, redirectUri);

        // We should have the code, which we can trade for the token
        final String code = authorizationResponse.getCode();

        System.out.print("Authorization Code: ");
        System.out.println(code);

        return code;
    }// end of requestAuthCode().

    /**
     * Given an authorization code, calls the auth server to request a token
     * @param code authorization code.
     * @return the accessToken.
     * @throws URISyntaxException URISyntaxException
     * @throws IOException IOException
     */
    public String getTokenForCode(String code) throws URISyntaxException, IOException
    {
        // The token request URL
        final String tokenUrl = "https://" + blizzardDomain + "/oauth2/default/v1/token";

        // The original redirect URL
        final URI redirectUri = new URI(this.redirectUri);

        // Using HttpClient to make the POST to exchange the auth code for the token
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(tokenUrl);

        // Adding the POST params to the request
        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("grant_type", grantType));
        urlParameters.add(new BasicNameValuePair("code", code));
        urlParameters.add(new BasicNameValuePair("redirect_uri", redirectUri.toString()));
        urlParameters.add(new BasicNameValuePair("client_id", clientId));
        urlParameters.add(new BasicNameValuePair("client_secret", clientSecret));
        urlParameters.add(new BasicNameValuePair("scope", scope));

        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        // Execute the request
        HttpResponse response = client.execute(post);

        // Print the status code
        System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

        // Get the content as a String
        String content = EntityUtils.toString(response.getEntity());

        System.out.println("Result : " + content.toString());

        return content.toString();
    }// end of getTokenForCode().

    /**
     * FXML button to open oauth login window.
     * @throws AuthorizationException AuthorizationException
     * @throws IOException IOException
     * @throws URISyntaxException URISyntaxException
     */
    @FXML
    void login() throws AuthorizationException, IOException, URISyntaxException
    {
        requestAuthCode();
    }// end of login().

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }// end of initialize().
}// end of class.