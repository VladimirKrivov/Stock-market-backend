package stock.market.backend.app.controllers.impl;

import org.springframework.http.ResponseEntity;
import stock.market.backend.app.models.dto.UserDto;

public interface UserControllerImpl {

    ResponseEntity<UserDto> register(UserDto dto);
    ResponseEntity<UserDto> login(UserDto dto);


}
