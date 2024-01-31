package stock.market.backend.app.controllers;

import jakarta.transaction.Transactional;
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

/**
 * Контроллер обработки запросов связанных с акциями
 */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/stock")
public class StockController implements StockControllerImpl{

    // Объект stocks service
    private final StocksService stocksService;



    /**
     * Метод позволяет получить акцию по ее названию для конкретного пользователя
     * @param company название акции
     * @param name имя пользователя
     * @return stockDto найденная или добавленная акция в виде dto
     */
    @Override
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/find"
    )
    public ResponseEntity<StockDto> createStock(@RequestParam String company,
                                                @RequestParam String name) {

        StockDto stockDto = stocksService.findStock(company, name);

        return new ResponseEntity<>(stockDto, HttpStatus.CREATED);
    }


    /**
     * Метод позволяет получить портфель акций конкретного пользователя
     * @param name имя пользователя
     * @return List<StockDto> - список всех акций пользователя
     */
    @Override
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/find/all"
    )
    public ResponseEntity<List<StockDto>> findAllToUser(@RequestParam String name) {

        List<StockDto> stockDto = stocksService.findAllStockForUser(name);

        return new ResponseEntity<>(stockDto, HttpStatus.OK);
    }

    /**
     * Метод позволяет едалить акцию из портфеля пользователя
     * @param name имя пользователя
     * @param secid sec id акции
     * @return HttpStatus.OK
     */
    @Transactional
    @Override
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
