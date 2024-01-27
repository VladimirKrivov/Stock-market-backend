package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import stock.market.backend.app.models.dto.UserDto;
import stock.market.backend.app.models.entity.User;
import stock.market.backend.app.repositories.UserRepositories;
import stock.market.backend.app.services.impl.UserServiceImpl;
import stock.market.backend.app.util.Mapper;

@Service
@AllArgsConstructor
public class UserService implements UserServiceImpl {

    private final UserRepositories userRepositories;
    private final Mapper mapper;


    @Override
    public UserDto register(UserDto dto) {
        User user = userRepositories.findUsersByName(dto.getName());
        UserDto userDto = null;

        if (user == null) {
            User us = userRepositories.save(mapper.userDtoToUser(dto));
            return mapper.userToUserDto(us);
        } else
            return userDto;
    }

    @Override
    public UserDto login(UserDto dto) {
        User user = userRepositories.findUsersByName(dto.getName());
        UserDto userDto = null;
        if (user != null) {
            if (user.getPassword().equals(dto.getPassword())) {
                return mapper.userToUserDto(user);
            } else {
                return userDto;
            }

        } else
            return userDto;
    }
}
