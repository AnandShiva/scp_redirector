package redirector;

import redirector.auth.*;
import redirector.model.Destination;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    @Autowired
    EnvironmentVariables environmentVariables;
    @Autowired
    AuthorizationManager authManager;
    @Autowired
    DestinationsManager destManager;
    @Autowired
    ConnectionManager connManager;

    @RequestMapping("/destinations")
    public String getDestinations() {
        String destinations = "";
        try {
            destinations = destManager.getDestinations();
            destinations = "List of destinations ! " + authManager.getDestinationAuthToken() + "\n" + destinations;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return destinations;
    }

    @RequestMapping("/redirect/{destinationName}")
    public String proxyRequest(@PathVariable("destinationName") String destinationName) {
        String responseData = "";
        try {
            destManager.getDestinations();
            Destination connectionSystem = destManager.getDestinationbyName(destinationName);
            responseData = connManager.proxyAndFetchData(connectionSystem);
        } catch (IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseData;
    }
}