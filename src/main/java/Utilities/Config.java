/*
 * Name: Bobby Jonkman
 * Date: April.29.2021
 * Purpose: This class provides the configuration needed for endpoint requesting.
 */

package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.microsoft.alm.oauth2.useragent.AuthorizationResponse;
import com.microsoft.alm.oauth2.useragent.UserAgent;
import com.microsoft.alm.oauth2.useragent.UserAgentImpl;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Config {
    // instance variables, these are pulled from gradle.properties.
    private String blizzardDomain;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String scope;
    private String grantType;
    private String user;
    private String pass;

    /**
     * Loads our config info from the app.properties file
     */
    private void loadAppProperties() {
        InputStream inputStream = Config.class.getClassLoader().getResourceAsStream("app.properties");
        Properties appProps = new Properties();

        // try-catch in case theres a null error when loading data from app.properties.
        try {
            appProps.load(inputStream);
        } catch(IOException e) {
            System.out.println("app.properties load error...");
        }// end of try-catch.

        blizzardDomain = appProps.getProperty("blizzardDomain");
        clientId = appProps.getProperty("blizzardClientId");
        clientSecret = appProps.getProperty("blizzardClientSecret");
        redirectUri = appProps.getProperty("redirectUri");
        scope = appProps.getProperty("scope");
        grantType = appProps.getProperty("grantType");
        user = appProps.getProperty("user");
        pass = appProps.getProperty("pass");
    }// end of loadAppProperties().

    /**
     * Build the authorization request URL
     *
     * @return the authorization URL.
     * @throws URISyntaxException    URISyntaxException
     * @throws MalformedURLException MalformedURLException
     */
    private URI buildAuthorizationUrl() throws URISyntaxException, MalformedURLException {
        URIBuilder builder = new URIBuilder();

        builder.setScheme("https");
        builder.setHost(blizzardDomain);
        builder.setPath("/oauth/authorize");
        builder.addParameter("client_id", clientId);
        builder.addParameter("redirect_uri", redirectUri);
        builder.addParameter("response_type", "code");
        builder.addParameter("scope", scope);

        URL url = builder.build().toURL();

        return url.toURI();
    }// end of buildAuthorizationUrl().

    /**
     * Requests an authorization code from the auth server
     */
    public String requestAuthorizationCode() {
        // load properties from app.properties.
        loadAppProperties();

        // local variables.
        URI authorizationEndpoint = null;
        URI redirectUri = null;
        UserAgent userAgent = null;
        AuthorizationResponse authorizationResponse = null;

        try {
            // Generate the auth endpoint URI to request the authCode.
            authorizationEndpoint = buildAuthorizationUrl();
            redirectUri = new URI(this.redirectUri);

            // Create the user agent and make the call to the auth endpoint.
            userAgent = new UserAgentImpl();

            // opens the browser window for the user to login.
            authorizationResponse = userAgent.requestAuthorizationCode(authorizationEndpoint, redirectUri);
        } catch(Exception e) {
            System.out.println("Authorization Error...");
        }// end of try-catch.

        return authorizationResponse.getCode();
    }// end of requestAuthCode().

    /**
     * Given an authorization code, calls the auth server to request a token
     *
     * @throws URISyntaxException URISyntaxException
     * @throws IOException        IOException
     */
    public void requestAccessToken() throws URISyntaxException, IOException {
        loadAppProperties();

        // The token request URL.
        final String tokenUrl = "https://us.battle.net/oauth/token";

        // The original redirect URL.
        final URI redirectUri = new URI(this.redirectUri);

        // Using HttpClient to make the POST to exchange the auth code for the token.
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(tokenUrl);

        // Adding the POST params to the request.
        List<NameValuePair> urlParameters = new ArrayList<>();
        urlParameters.add(new BasicNameValuePair("grant_type", grantType));
        urlParameters.add(new BasicNameValuePair("code", requestAuthorizationCode()));
        urlParameters.add(new BasicNameValuePair("redirect_uri", redirectUri.toString()));
        urlParameters.add(new BasicNameValuePair("client_id", clientId));
        urlParameters.add(new BasicNameValuePair("client_secret", clientSecret));
        urlParameters.add(new BasicNameValuePair("scope", scope));

        // add parameters to url.
        post.setEntity(new UrlEncodedFormEntity(urlParameters));

        // Execute the request.
        HttpResponse response = client.execute(post);

        // Get the content as a String.
        String content = EntityUtils.toString(response.getEntity());

        // parse the json String.
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(content);
        JsonObject jsonObject = element.getAsJsonObject();

        String accessToken = jsonObject.get("access_token").getAsString();

        // insert the accessToken into the database.
        try {
            // db connection.
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/wowdb", user, pass);

            // first empty the table as we only ever want ONE entry in the table.
            Statement statement = conn.createStatement();
            String deleteQuery = "DELETE FROM credentials";
            statement.execute(deleteQuery);

            // the create the preparedStatement to insert into the database.
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO credentials(accessToken) values (?)");
            preparedStatement.setString(1, accessToken);
            preparedStatement.execute();

            // close connections.
            preparedStatement.close();
            conn.close();
        } catch(Exception e) {
            e.printStackTrace();
        }// end of try-catch.
    }// end of getAccessToken().
}// end of class.
