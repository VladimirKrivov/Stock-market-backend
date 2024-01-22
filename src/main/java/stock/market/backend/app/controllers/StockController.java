package stock.market.backend.app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import stock.market.backend.app.services.ApiService;
import stock.market.backend.app.services.StockSearch;

@RestController
@AllArgsConstructor
public class StockController {

    private final ApiService apiService;
//    private final StockSearch stockSearch;

    @GetMapping("/test")
    public String testController(){
        return apiService.testMet();
//        return stockSearch.
//        return null;
    }
}
