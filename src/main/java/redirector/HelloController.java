package redirector;

import redirector.auth.*;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/destinations")
    public String getDestinations() throws URISyntaxException {
        //AuthorizationManager authManager = new AuthorizationManager("", "", "");
        return "List of destinations ! ";//+authManager.getAuthTokenString();
    }

}