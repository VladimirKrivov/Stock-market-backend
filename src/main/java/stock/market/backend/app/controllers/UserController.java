package stock.market.backend.app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import stock.market.backend.app.controllers.impl.UserControllerImpl;
import stock.market.backend.app.models.dto.ShortUserDto;
import stock.market.backend.app.models.dto.UserDto;
import stock.market.backend.app.services.UserService;

/**
 * Контроллер обработки запросов связанных с авторизацией и регистрацией
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController implements UserControllerImpl {


    private final UserService userService;



    /**
     * Метод регистрации
     * @param dto запрос клиента
     * @return ShortUserDto ответ клиенту в виде dto
     */
    @Override
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/register"
    )
    public ResponseEntity<ShortUserDto> register(@RequestBody UserDto dto) {
        ShortUserDto userDto = userService.register(dto);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    /**
     * Метод авторизации
     * @param dto запрос клиента
     * @return ShortUserDto ответ клиенту в виде dto
     */
    @Override
    @RequestMapping(
            method = RequestMethod.POST,
            value = "/login"
    )
    public ResponseEntity<ShortUserDto> login(@RequestBody UserDto dto) {
        ShortUserDto userDto = userService.login(dto);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
