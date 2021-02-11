package org.notabarista.controller;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;
import org.notabarista.controller.abstr.AbstractDeleteController;
import org.notabarista.dto.NothingDTO;
import org.notabarista.entity.NothingEntity;
import org.notabarista.entity.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/nothing")
public class NothingController extends AbstractDeleteController<NothingEntity, NothingDTO> {

    @Autowired
    public Client client;

    @GetMapping("/test-header")
    public String testHeader(@RequestHeader Map<String, String> headers) {
        StringBuilder sb = new StringBuilder();
        headers.forEach((k, v) -> {
            System.out.println(k + " : " + v);
            sb.append(k + " : " + v + "<br>");
        });

        return sb.toString();
    }

    @GetMapping("/test-okta-user")
    public String testOktaHeader(@RequestHeader("uid") String userId) {
        User user = client.getUser(userId);
        StringBuilder sb = new StringBuilder();
        if (user != null) {
            sb.append("<p><h2>Logged in user details:</h2></p>");
            sb.append("<b>Profile: </b>" + user.getProfile() + "<br>");
            sb.append("<b>Created: </b>" + user.getCreated() + "<br>");
            sb.append("<b>Activated: </b>" + user.getActivated() + "<br>");
            sb.append("<b>Last login: </b>" + user.getLastLogin() + "<br>");
            sb.append("<b>Status: </b>" + user.getStatus() + "<br>");
            sb.append("<b>Embedded: </b>" + user.getEmbedded() + "<br>");
            sb.append("<b>Links: </b>" + user.getLinks() + "<br>");
            sb.append("<b>Credentials -> emails: </b>" + user.getCredentials().getEmails() + "<br>");
            sb.append("<b>Credentials -> provider: </b>" + user.getCredentials().getProvider() + "<br>");
            sb.append("<b>Credentials -> recovery question: </b>" + user.getCredentials().getRecoveryQuestion() + "<br>");
        } else {
            sb.append("User with id '" + userId + "' not found");
        }

        return sb.toString();
    }
    
    @GetMapping("/special")
    public Response<String> getSpecial() {
    	
    	return null;
    }

}
