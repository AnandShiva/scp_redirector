package redirector;

import redirector.auth.*;
import redirector.model.Destination;
import redirector.model.RedirectRequestDTO;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value="/redirect",method = RequestMethod.POST)
    public @ResponseBody String proxyRequest(RedirectRequestDTO redirectRequest){
        String destinationName = redirectRequest.getDestinationName();
        String subPath = redirectRequest.getSubPath();
        String responseData = "";
        try {
            destManager.getDestinations();
            Destination connectionSystem = destManager.getDestinationbyName(destinationName);
            responseData = connManager.proxyAndFetchData(connectionSystem,subPath);
        } catch (IOException | URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return responseData;
    }
}