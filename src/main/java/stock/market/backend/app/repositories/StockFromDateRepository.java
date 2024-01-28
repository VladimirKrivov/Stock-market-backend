package stock.market.backend.app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import stock.market.backend.app.models.entity.StockFromDate;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface StockFromDateRepository extends CrudRepository<StockFromDate, UUID> {

    List<StockFromDate> findByTradeDateBetweenAndSecId(LocalDate startDate, LocalDate endDate, String secId);

    StockFromDate findByTradeDateAndSecId(LocalDate tradeDate, String secId);
}
