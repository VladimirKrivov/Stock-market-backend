package stock.market.backend.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stock.market.backend.app.models.entity.History;

import java.util.UUID;

@Repository
public interface HistoryRepository extends CrudRepository<History, UUID> {
}
