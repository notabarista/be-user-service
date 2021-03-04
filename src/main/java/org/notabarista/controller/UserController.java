package org.notabarista.controller;

import org.apache.http.HttpStatus;
import org.notabarista.controller.abstr.AbstractDeleteController;
import org.notabarista.dto.UserDTO;
import org.notabarista.entity.UserEntity;
import org.notabarista.exception.AbstractNotabaristaException;
import org.notabarista.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractDeleteController<UserEntity, UserDTO> {

    @Autowired
    private IUserService userService;

    @GetMapping("/register")
    public void register(@RequestHeader("uid") String userId, HttpServletResponse httpServletResponse) throws AbstractNotabaristaException {
        userService.processUser(userId);

        // TODO is this the best way to redirect from service URL to gateway URL?
        httpServletResponse.setHeader("Location", "/");
        httpServletResponse.setStatus(HttpStatus.SC_MOVED_TEMPORARILY);
    }

}
