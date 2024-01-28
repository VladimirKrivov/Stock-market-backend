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

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/stock")
public class StockController{

    private final StocksService stocksService;


    @RequestMapping(
            method = RequestMethod.GET,
            value = "/find"
    )
    public ResponseEntity<StockDto> createStock(@RequestParam String company,
                                                @RequestParam String name) {

        StockDto stockDto = stocksService.findStock(company, name);

        return new ResponseEntity<>(stockDto, HttpStatus.CREATED);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/find/all"
    )
    public ResponseEntity<List<StockDto>> findAllToUser(@RequestParam String name) {

        List<StockDto> stockDto = stocksService.findAllStockForUser(name);

        return new ResponseEntity<>(stockDto, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            value = "/find/delete"
    )
    public ResponseEntity<Void> deleteStockUser(@RequestParam String name,
                                                @RequestParam String secid) {

        stocksService.deleteForUser(name, secid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
