package redirector.auth;

import org.cloudfoundry.identity.client.UaaContext;
import org.cloudfoundry.identity.client.UaaContextFactory;
import org.cloudfoundry.identity.client.token.GrantType;
import org.cloudfoundry.identity.client.token.TokenRequest;
import org.cloudfoundry.identity.uaa.oauth.token.CompositeAccessToken;

import java.net.URI;
import java.net.URISyntaxException;

public class AuthorizationManager {
    private String userId;
    private String password;
    private URI xsuaaUrl;
    CompositeAccessToken accessToken;

    public AuthorizationManager(String userId, String password, String url) throws URISyntaxException {
        this.userId = userId;
        this.password = password;
        // URL section of XSUAA env variable.
        this.xsuaaUrl = new URI(url);

        this.userId = "sb-clone889a10d6bf704c69bd61afe7c9adbb78!b6037|destination-xsappname!b433";
        this.password = "9wfsV7MtKwKjGXayumt1//zg6o0=";
        this.xsuaaUrl = new URI("https://i332878trial.authentication.sap.hana.ondemand.com");

        // Make request to UAA to retrieve JWT
        UaaContextFactory factory = UaaContextFactory.factory(xsuaaUrl);
        TokenRequest tokenRequest = factory.tokenRequest();
        tokenRequest.setGrantType(GrantType.CLIENT_CREDENTIALS);
        tokenRequest.setClientId(this.userId);
        tokenRequest.setClientSecret(this.password);
        UaaContext xsUaaContext = factory.authenticate(tokenRequest);
        this.accessToken = xsUaaContext.getToken();
    }

    public String getAuthTokenString(){
        return this.accessToken.getValue();
    }
}