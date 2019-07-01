package redirector.auth;

import org.cloudfoundry.identity.client.UaaContext;
import org.cloudfoundry.identity.client.UaaContextFactory;
import org.cloudfoundry.identity.client.token.GrantType;
import org.cloudfoundry.identity.client.token.TokenRequest;
import org.cloudfoundry.identity.uaa.oauth.token.CompositeAccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import redirector.EnvironmentVariables;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@ConfigurationProperties(prefix = "auth")
public class AuthorizationManager {
    @Autowired
    private EnvironmentVariables env;
    private URI xsuaaUrl;
    CompositeAccessToken destinationAccessToken;
    CompositeAccessToken connectivityAccessToken;

    public AuthorizationManager() throws URISyntaxException {
        // env = new EnvironmentVariables();
        // this.userId = env.authClientId;
        // this.password = env.authClientSecret;
        // // URL section of XSUAA env variable.
        // this.xsuaaUrl = new URI(env.authServiceUrl);

        // this.userId = "sb-clone889a10d6bf704c69bd61afe7c9adbb78!b6037|destination-xsappname!b433";
        // this.password = "9wfsV7MtKwKjGXayumt1//zg6o0=";
        // this.xsuaaUrl = new URI("https://i332878trial.authentication.sap.hana.ondemand.com");
        //refreshToken();
    }

    private void refreshDestinationToken() throws URISyntaxException {
        // URL section of XSUAA env variable.
        xsuaaUrl = new URI(env.authServiceUrl);
        // Make request to UAA to retrieve JWT
        UaaContextFactory factory = UaaContextFactory.factory(xsuaaUrl);
        TokenRequest tokenRequest = factory.tokenRequest();
        tokenRequest.setGrantType(GrantType.CLIENT_CREDENTIALS);
        tokenRequest.setClientId(env.destinationClientId);
        tokenRequest.setClientSecret(env.destinationClientSecret);
        UaaContext xsUaaContext = factory.authenticate(tokenRequest);
        this.destinationAccessToken = xsUaaContext.getToken();
    }

    private void refresConnectivityToken() throws URISyntaxException {

        // URL section of XSUAA env variable.
        xsuaaUrl = new URI(env.authServiceUrl);
        // Make request to UAA to retrieve JWT
        UaaContextFactory factory = UaaContextFactory.factory(xsuaaUrl);
        TokenRequest tokenRequest = factory.tokenRequest();
        tokenRequest.setGrantType(GrantType.CLIENT_CREDENTIALS);
        tokenRequest.setClientId(env.connectivityClientId);
        tokenRequest.setClientSecret(env.connectivityClientSecret);
        UaaContext xsUaaContext = factory.authenticate(tokenRequest);
        connectivityAccessToken = xsUaaContext.getToken();
    }

    public String getDestinationAuthToken() throws URISyntaxException {
        if (destinationAccessToken == null || destinationAccessToken.isExpired()) {
            refreshDestinationToken();
        }
        return destinationAccessToken.getValue();
    }

    public String getConnectionAuthToken() throws URISyntaxException {
        if (connectivityAccessToken== null || connectivityAccessToken.isExpired()) {
            refresConnectivityToken();
        }
        return connectivityAccessToken.getValue();
    }
}