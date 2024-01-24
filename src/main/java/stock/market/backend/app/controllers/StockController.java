package stock.market.backend.app.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import stock.market.backend.app.controllers.impl.StockControllerImpl;
import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.services.StocksService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/stock")
public class StockController implements StockControllerImpl {

    private final StocksService stocksService;


    @Override
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/find"
    )
    public ResponseEntity<StockDto> createStock(@RequestParam String name) {
        StockDto stockDto = stocksService.findStock(name);
        return new ResponseEntity<>(stockDto, HttpStatus.CREATED);
    }
}
