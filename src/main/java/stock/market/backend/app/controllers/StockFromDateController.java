package stock.market.backend.app.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.market.backend.app.controllers.impl.StockFromDateControllerImpl;
import stock.market.backend.app.models.dto.StockFromDateDto;
import stock.market.backend.app.services.StockFromDateService;
import stock.market.backend.app.util.Mapper;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/history")
public class StockFromDateController implements StockFromDateControllerImpl {

    private final StockFromDateService historyService;
    private final Mapper mapper;


    // Узнать стоимость закрытия акции по имени за определенный промежуток времени
    @Override
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/find/{company}"
    )
    public ResponseEntity<List<StockFromDateDto>> findStocksFromDate(@PathVariable String company,
                                                              @RequestParam("from") String from,
                                                              @RequestParam("till") String till) {


        return new ResponseEntity<>(mapper.listHistoryToHistoryDto(historyService.getHistory(company, from, till)), HttpStatus.CREATED);
    }
}
