package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import stock.market.backend.app.models.dto.ShortUserDto;
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

    private final PasswordEncoder passwordEncoder;


    // Сервис выполняющий регистрацию
    @Override
    public ShortUserDto register(UserDto dto) {
        User user = userRepositories.findUsersByName(dto.getName());
        ShortUserDto userDto = null;

        if (user == null) {
            User us = userRepositories.save(mapper.userDtoToUser(dto));
            return mapper.userToShortUserDto(us);
        } else
            return userDto;
    }

    // Сервис выполняющий авторизацию
    @Override
    public ShortUserDto login(UserDto dto) {
        User user = userRepositories.findUsersByName(dto.getName());
        ShortUserDto userDto = null;
        if (user != null) {
//            if (user.getPassword().equals(passwordEncoder.encode(dto.getPassword()))) {
            if (passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
                return mapper.userToShortUserDto(user);
            } else {
                return userDto;
            }

        } else
            return userDto;
    }
}
