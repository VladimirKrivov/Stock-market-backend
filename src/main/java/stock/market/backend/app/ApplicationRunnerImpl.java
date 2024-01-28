package stock.market.backend.app;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import stock.market.backend.app.models.entity.User;
import stock.market.backend.app.repositories.UserRepositories;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Locale;

@Slf4j
@Component
@AllArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunner {
    private final UserRepositories userRepositories;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        Faker faker = new Faker(new Locale("ru"));

        User user = new User();

        user.setName("John");
        user.setPassword("12346");
        userRepositories.save(user);

    }
}
