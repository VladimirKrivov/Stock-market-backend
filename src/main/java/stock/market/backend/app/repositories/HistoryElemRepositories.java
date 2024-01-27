package stock.market.backend.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryElemRepositories extends CrudRepository<stock.market.backend.app.models.entity.HistoryElem, UUID> {
}
