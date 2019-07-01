package redirector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import redirector.auth.AuthorizationManager;
import redirector.model.*;

@Service
public class DestinationsManager {
    Destination destinationCollection[];
    @Autowired
    private EnvironmentVariables env;
    @Autowired
    private AuthorizationManager authManager;
    URL destinationServiceUrl;
    String urlPath = "/destination-configuration/v1/subaccountDestinations/";

    public DestinationsManager() {
    }

    
    public Destination getDestinationbyName(String destinationName){
        Destination requiredDestination = null;
        for(Destination destination : destinationCollection){
            if(destinationName.equals(destination.getName())){
                requiredDestination = destination;
            }
        }
        return requiredDestination;
    }

    public String getDestinations() throws IOException, URISyntaxException {
        destinationServiceUrl = new URL(env.destinationServiceUrl+urlPath);
        URLConnection connection = destinationServiceUrl.openConnection();
        connection.setRequestProperty("Authorization", "Bearer " + authManager.getDestinationAuthToken());
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        String responseDestination = content.toString();
        Gson gson = new Gson();
        destinationCollection = gson.fromJson(responseDestination, Destination[].class);
        return responseDestination;
    }

}