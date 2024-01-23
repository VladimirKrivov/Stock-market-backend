package stock.market.backend.app.controllers;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.market.backend.app.controllers.impl.StockControllerImpl;
import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.services.ApiService;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/stock")
public class StockController implements StockControllerImpl {

    private final ApiService apiService;


    @Override
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/find"
    )
    public ResponseEntity<StockDto> createStock(@RequestParam String name) {
        StockDto stockDto = apiService.findStock(name);
        return new ResponseEntity<>(stockDto, HttpStatus.CREATED);
    }
}
