package org.notabarista.controller;

import java.util.Arrays;

import org.apache.commons.lang3.time.StopWatch;
import org.notabarista.entity.CanAccessDetails;
import org.notabarista.entity.response.Response;
import org.notabarista.entity.response.ResponseStatus;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.IUserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping("/user/check-access")
public class CheckAccessController {

	@Autowired
	private IUserAccessService userAccessService;

	@PostMapping("/")
	public ResponseEntity<Response<Boolean>> checkAccess(@RequestBody CanAccessDetails accessDetails)
			throws AbstractNotabaristaException {

		StopWatch watch = new StopWatch();
		watch.start();
		log.debug("checking access for: " + accessDetails);

		boolean canAccess = userAccessService.canAccess(accessDetails.getUid(), accessDetails.getAction(),
				accessDetails.getResource(), accessDetails.getMicroserviceName());

		watch.stop();
		return new ResponseEntity<>(new Response<Boolean>(ResponseStatus.SUCCESS, watch.getTime(),
				Arrays.asList(canAccess), 0, 0, 0, 0, ""), HttpStatus.OK);
	}
}
