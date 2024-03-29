package redirector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import redirector.auth.AuthorizationManager;
import redirector.model.Destination;

@Configuration
public class ConnectionManager {
    @Autowired
    AuthorizationManager authManager;
    @Autowired
    EnvironmentVariables env;

    public String proxyAndFetchData(Destination destination, String subPath) throws IOException, URISyntaxException {

        String connProxyHost = env.connectivityOnPremProxyHost;
        int connProxyPort = Integer.parseInt(env.connectivityOnPremProxyPort);
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(connProxyHost, connProxyPort));
        String sysURLString = destination.getURL() + subPath;
        if ((destination.getSysClient() != null) && (!destination.getSysClient().isEmpty())) {
            String sysClient = destination.getSysClient();
            if (sysClient.contains("?")) {
                sysURLString = sysURLString + "&sap-client=" + sysClient;
            } else {
                sysURLString = sysURLString + "?sap-client=" + sysClient;
            }
        }
        System.out.println("*************LOG************ - onpremConectionURL -" + sysURLString + destination.getUser()
                + destination.getPassword());
        URL sysUrl = new URL(sysURLString);
        URLConnection urlConnection = sysUrl.openConnection(proxy);
        urlConnection.setRequestProperty("Proxy-Authorization", "Bearer " + authManager.getConnectionAuthToken());
        // Basic authentication to backend system
        String credentials = MessageFormat.format("{0}:{1}", destination.getUser(), destination.getPassword());
        byte[] encodedBytes = Base64.getEncoder().encode(credentials.getBytes());
        urlConnection.setRequestProperty("Authorization", "Basic " + new String(encodedBytes));
        // urlConnection.setRequestProperty("X-CSRF-Token", "Fetch");
        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
}