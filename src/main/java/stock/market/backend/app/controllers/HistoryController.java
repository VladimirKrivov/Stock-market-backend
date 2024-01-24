package stock.market.backend.app.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.market.backend.app.controllers.impl.HistoryControllerImpl;
import stock.market.backend.app.models.dto.HistoryDto;
import stock.market.backend.app.services.HistoryService;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/api/v1/history")
public class HistoryController implements HistoryControllerImpl {

    private final HistoryService historyService;

    @RequestMapping(
            method = RequestMethod.GET,
            value = "/find/{company}"
    )
    public ResponseEntity<List<HistoryDto>> findHistory(@PathVariable String company,
                                                        @RequestParam("from") String from,
                                                        @RequestParam("till") String till) {


        return new ResponseEntity<>(historyService.getHistory(company, from, till), HttpStatus.CREATED);
    }
}
