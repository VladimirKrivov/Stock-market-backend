package stock.market.backend.app.controllers.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import stock.market.backend.app.models.dto.StockFromDateDto;

import java.util.List;

public interface StockFromDateControllerImpl {
    ResponseEntity<List<StockFromDateDto>> findStocksFromDate(@PathVariable String company, String from, String till);
}
