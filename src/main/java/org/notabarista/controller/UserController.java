package org.notabarista.controller;

import org.notabarista.controller.abstr.AbstractDeleteController;
import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.IUserService;
import org.notabarista.util.NABConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
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

}
