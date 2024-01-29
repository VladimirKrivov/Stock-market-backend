package stock.market.backend.app.controllers.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import stock.market.backend.app.models.dto.StockDto;

import java.util.List;

public interface StockControllerImpl {

    ResponseEntity<StockDto> createStock(String company, String name);
    ResponseEntity<List<StockDto>> findAllToUser(String name);

    ResponseEntity<Void> deleteStockUser(String name, String secid);
}
