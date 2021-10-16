package org.notabarista.controller;

import org.apache.commons.lang3.time.StopWatch;
import org.notabarista.controller.abstr.AbstractDeleteController;
import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.notabarista.entity.response.Response;
import org.notabarista.entity.response.ResponseStatus;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.IUserService;
import org.notabarista.util.NABConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractDeleteController<UserEntity, UserDTO> {

    @Autowired
    private IUserService userService;

    @GetMapping("/register")
    public void register(@RequestHeader(NABConstants.UID_HEADER_NAME) String userId, HttpServletResponse httpServletResponse) throws AbstractNotabaristaException {
        userService.processUser(userId);

        httpServletResponse.setHeader("Location", "/");
        httpServletResponse.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
    }

    @GetMapping("/current")
    public ResponseEntity<UserDTO> getCurrentUserDetails(@RequestHeader(NABConstants.UID_HEADER_NAME) String userId) {
        Optional<UserDTO> userDTOOptional = userService.findByUserIdentifier(userId);

        if (userDTOOptional.isPresent()) {
            return new ResponseEntity<>(userDTOOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/identifier/{identifier}")
    public ResponseEntity<Response<UserDTO>> getUserByIdentifier(@PathVariable String identifier) {
        StopWatch watch = new StopWatch();
        watch.start();
        Optional<UserDTO> userDTOOptional = userService.findByUserIdentifier(identifier);
        List<UserDTO> dtos = userDTOOptional.isPresent() ? List.of(userDTOOptional.get()) : new ArrayList<>();
        watch.stop();

        if (userDTOOptional.isPresent()) {
            return new ResponseEntity<>(
                    new Response<>(ResponseStatus.SUCCESS, watch.getTime(), dtos, dtos.size(), 1, 0, dtos.size(), ""),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
