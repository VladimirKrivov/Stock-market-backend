package stock.market.backend.app.services.impl;


import stock.market.backend.app.models.dto.UserDto;

public interface UserServiceImpl {
    UserDto register(UserDto dto);

    UserDto login(UserDto dto);
}
