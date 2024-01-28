package stock.market.backend.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stock.market.backend.app.models.entity.User;

import java.util.UUID;

@Repository
public interface UserRepositories extends CrudRepository<User, UUID> {
    User findUsersByName(String name);
}
