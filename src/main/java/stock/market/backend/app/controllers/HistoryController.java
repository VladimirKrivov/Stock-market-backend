package stock.market.backend.app.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.market.backend.app.controllers.impl.HistoryControllerImpl;
import stock.market.backend.app.models.dto.HistoryDto;
import stock.market.backend.app.models.dto.ShortHistoryDto;
import stock.market.backend.app.services.HistoryService;

import java.util.List;

/**
 Класс контроллера для работы с историей расчетов акций пользователя.
 Отвечает за обработку запросов, связанных с произведением расчетов акций в портфеле пользователя и получением истории запросов пользователя. */
@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/calc")
public class HistoryController implements HistoryControllerImpl {

    // Контроллер с помощью которого идет обращение для произведения расчетов акций в портфеле пользователя
    private final HistoryService historyService;

    /**
     Расчитывает темп прироста выбранных акций за определенную дату.
     @param dto данные запроса с выбранными акциями и датой
     @return объект ResponseEntity с результатом расчетов акций и статусом CREATED */
    @Override
    @RequestMapping(method = RequestMethod.POST, value = "/stocks")
    public ResponseEntity<HistoryDto> calcStocks(@RequestBody ShortHistoryDto dto) {

        HistoryDto historyDto = historyService.calcPricesStocks(dto);
        return new ResponseEntity<>(historyDto, HttpStatus.CREATED);
    }

    /**
     Получает историю запросов пользователя.
     @param username имя пользователя
     @return объект ResponseEntity с историей запросов пользователя и статусом OK */
    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/stocks/history/{username}")
    public ResponseEntity<List<HistoryDto>> getHistoryUser(@PathVariable String username) {

        List<HistoryDto> historyDto = historyService.findHistoryToUser(username);

        return new ResponseEntity<>(historyDto, HttpStatus.OK);
    }

}
