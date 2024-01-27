package stock.market.backend.app.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import stock.market.backend.app.models.dto.ShortHistoryDto;
import stock.market.backend.app.models.dto.StockFromDateListDto;
import stock.market.backend.app.services.HistoryService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/calc")
public class HistoryController {

    private final HistoryService historyService;

    //Расчитать темп прироста выбранных акций за определенную дату
    @RequestMapping(method = RequestMethod.GET, value = "/stocks")
    public ResponseEntity<StockFromDateListDto> calcStocks(@RequestBody ShortHistoryDto dto) {
//        StockFromDateListDto listDto =
        historyService.calcPricesStocks(dto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
