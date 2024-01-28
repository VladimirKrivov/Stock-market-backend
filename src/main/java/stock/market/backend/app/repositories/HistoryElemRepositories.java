package stock.market.backend.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stock.market.backend.app.models.entity.HistoryElem;

import java.util.List;
import java.util.UUID;

@Repository
public interface HistoryElemRepositories extends CrudRepository<stock.market.backend.app.models.entity.HistoryElem, UUID> {
    List<HistoryElem> findAllByHistory_Id(UUID historyId);
}
