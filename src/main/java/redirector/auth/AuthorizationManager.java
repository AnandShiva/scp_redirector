package redirector.auth;

import java.net.URI;
import java.net.URISyntaxException;

class AuthorizationManager {
    private String userId;
    private String password;
    private URI xsuaaUrl ;

    public AuthorizationManager(String userId, String password, String url) throws URISyntaxException {
        this.userId = userId;
        this.password = password;
        // URL section of XSUAA env variable.
        this.xsuaaUrl  = new URI(url);

        this.userId = "sb-clone889a10d6bf704c69bd61afe7c9adbb78!b6037|destination-xsappname!b433";
        this.password = "9wfsV7MtKwKjGXayumt1//zg6o0=";
        this.xsuaaUrl = new URI("https://i332878trial.authentication.sap.hana.ondemand.com");
    }

    private String fetchAuthToken() {
        String var = "";
        return "";

    }

    public String getAuthTokenString(){
        return fetchAuthToken();
    }
}