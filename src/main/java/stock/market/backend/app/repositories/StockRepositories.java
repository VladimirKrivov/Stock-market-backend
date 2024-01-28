package stock.market.backend.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stock.market.backend.app.models.entity.Stocks;
import stock.market.backend.app.models.entity.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockRepositories extends CrudRepository<Stocks, UUID> {
    Stocks findBySecId(String secId);

    List<Stocks> findAllByUser(User user);

    void deleteBySecIdAndUser(String secid, User user);
}
