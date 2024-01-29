package stock.market.backend.app.controllers.impl;

import org.springframework.http.ResponseEntity;
import stock.market.backend.app.models.dto.HistoryDto;
import stock.market.backend.app.models.dto.ShortHistoryDto;

import java.util.List;

public interface HistoryControllerImpl {
    //Абстракция для контроллера расчета портфеля акций
    ResponseEntity<HistoryDto> calcStocks(ShortHistoryDto dto);
    ResponseEntity<List<HistoryDto>> getHistoryUser(String username);
}
