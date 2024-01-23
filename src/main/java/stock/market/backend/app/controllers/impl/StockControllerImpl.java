package stock.market.backend.app.controllers.impl;

import org.springframework.http.ResponseEntity;
import stock.market.backend.app.models.dto.StockDto;

public interface StockControllerImpl {

    ResponseEntity<StockDto> createStock(String nameStock);
}
