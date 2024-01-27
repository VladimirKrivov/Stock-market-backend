package stock.market.backend.app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import stock.market.backend.app.controllers.impl.UserControllerImpl;
import stock.market.backend.app.models.dto.UserDto;
import stock.market.backend.app.services.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController implements UserControllerImpl {

    private final UserService userService;


    @Override
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/register"
    )
    public ResponseEntity<UserDto> register(UserDto dto) {
        UserDto userDto = userService.register(dto);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Override
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/login"
    )
    public ResponseEntity<UserDto> login(UserDto dto) {
        UserDto userDto = userService.register(dto);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
