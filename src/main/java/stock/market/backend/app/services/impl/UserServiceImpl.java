package stock.market.backend.app.services.impl;


import stock.market.backend.app.models.dto.ShortUserDto;
import stock.market.backend.app.models.dto.UserDto;

public interface UserServiceImpl {
    ShortUserDto register(UserDto dto);

    ShortUserDto login(UserDto dto);
}
