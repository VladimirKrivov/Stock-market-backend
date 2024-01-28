package stock.market.backend.app.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.market.backend.app.models.dto.HistoryDto;
import stock.market.backend.app.models.dto.ShortHistoryDto;
import stock.market.backend.app.models.dto.StockFromDateListDto;
import stock.market.backend.app.services.HistoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/calc")
public class HistoryController {

    private final HistoryService historyService;

    //Расчитать темп прироста выбранных акций за определенную дату
    @RequestMapping(method = RequestMethod.POST, value = "/stocks")
    public ResponseEntity<HistoryDto> calcStocks(@RequestBody ShortHistoryDto dto) {

        HistoryDto historyDto = new HistoryDto();
        historyDto = historyService.calcPricesStocks(dto);

//        System.out.println("Полученная DTO");
//        System.out.println(dto);

        return new ResponseEntity<>(historyDto, HttpStatus.CREATED);
    }

    //Получить историю запросов пользователя
    @RequestMapping(method = RequestMethod.GET, value = "/stocks/history/{username}")
    public ResponseEntity<List<HistoryDto>> getHistoryUser(@PathVariable String username) {

        List<HistoryDto> historyDto = historyService.findHistoryToUser(username);

        return new ResponseEntity<>(historyDto, HttpStatus.CREATED);
    }

}
