package org.notabarista.controller;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.user.User;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.StopWatch;
import org.notabarista.controller.abstr.AbstractDeleteController;
import org.notabarista.dto.NothingDTO;
import org.notabarista.entity.CanAccessDetails;
import org.notabarista.entity.NothingEntity;
import org.notabarista.entity.response.Response;
import org.notabarista.entity.response.ResponseStatus;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.IUserAccessService;
import org.notabarista.service.util.enums.MicroService;
import org.notabarista.util.NABConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/nothing")
public class NothingController extends AbstractDeleteController<NothingEntity, NothingDTO> {

	@Autowired
	private IUserAccessService userAccessService;

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
	public String testOktaHeader(@RequestHeader(NABConstants.UID_HEADER_NAME) String userId) {
		User user = client.getUser(userId);
		StringBuilder sb = new StringBuilder();
		if (user != null) {
			sb.append("<p><h2>Logged in user details:</h2></p>");
			sb.append("<b>OKTA Id: </b>" + user.getId() + "<br>");
			sb.append("<b>Profile: </b>" + user.getProfile() + "<br>");
			sb.append("<b>Created: </b>" + user.getCreated() + "<br>");
			sb.append("<b>Activated: </b>" + user.getActivated() + "<br>");
			sb.append("<b>Last login: </b>" + user.getLastLogin() + "<br>");
			sb.append("<b>Status: </b>" + user.getStatus() + "<br>");
			sb.append("<b>Embedded: </b>" + user.getEmbedded() + "<br>");
			sb.append("<b>Links: </b>" + user.getLinks() + "<br>");
			sb.append("<b>Credentials -> emails: </b>" + user.getCredentials().getEmails() + "<br>");
			sb.append("<b>Credentials -> provider: </b>" + user.getCredentials().getProvider() + "<br>");
			sb.append(
					"<b>Credentials -> recovery question: </b>" + user.getCredentials().getRecoveryQuestion() + "<br>");
		} else {
			sb.append("User with id '" + userId + "' not found");
		}

		return sb.toString();
	}

	@GetMapping("/special")
	public ResponseEntity<Response<String>> getSpecial(@RequestHeader(NABConstants.UID_HEADER_NAME) String userId)
			throws AbstractNotabaristaException {

		StopWatch watch = new StopWatch();
		watch.start();
		log.info("getSpecial uid: " + userId);

		userAccessService.checkAccess(CanAccessDetails.builder()
				.uid(userId)
				.action("special")
				.resource(this.getClass().getSimpleName())
				.microserviceName(MicroService.USER_MANAGEMENT_SERVICE.getMicroserviceName())
				.build());
		
		watch.stop();
		return new ResponseEntity<>(new Response<String>(ResponseStatus.SUCCESS, watch.getTime(),
				Arrays.asList("Special content"), 0, 0, 0, 0, ""), HttpStatus.OK);
	}
	
}
