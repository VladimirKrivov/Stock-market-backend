package stock.market.backend.app.controllers.impl;

import org.springframework.http.ResponseEntity;
import stock.market.backend.app.models.dto.ShortUserDto;
import stock.market.backend.app.models.dto.UserDto;

public interface UserControllerImpl {

    ResponseEntity<ShortUserDto> register( UserDto dto);
    ResponseEntity<ShortUserDto> login( UserDto dto);


}
