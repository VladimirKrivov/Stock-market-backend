package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock.market.backend.app.models.dto.HistoryDto;
import stock.market.backend.app.models.dto.ShortHistoryDto;
import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.models.dto.StockFromDateListDto;
import stock.market.backend.app.models.entity.History;
import stock.market.backend.app.models.entity.HistoryElem;
import stock.market.backend.app.models.entity.StockFromDate;
import stock.market.backend.app.repositories.HistoryElemRepositories;
import stock.market.backend.app.repositories.HistoryRepository;
import stock.market.backend.app.repositories.UserRepositories;
import stock.market.backend.app.services.impl.HistoryServiceImpl;
import stock.market.backend.app.util.Mapper;
import stock.market.backend.app.util.Parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryElemRepositories historyElemRepositories;
    private final Parser parser;
    private final Mapper mapper;
    private final StocksService stocksService;
    private final StockFromDateService stockFromDateService;
    private final UserRepositories userRepositories;

    public HistoryDto calcPricesStocks(ShortHistoryDto dto) {
        History history = new History();

        List<StockFromDate> stockFromDateList = new ArrayList<>();
        List<HistoryElem> historyElems = new ArrayList<>();

        LocalDate from = parser.parseDate(dto.getFrom());
        LocalDate till = parser.parseDate(dto.getTill());

        history.setFrom(from);
        history.setTill(till);

        history.setUser(userRepositories.findUsersByName(dto.getUserName()));

        History newHistory = historyRepository.save(history);


        List<StockDto> stockDto = dto.getStocksDto();

        for (StockDto elem : stockDto) {
            stockFromDateList.addAll(stockFromDateService.getHistory(elem.getShortname(), dto.getFrom(), dto.getTill()));
        }

        for (int i = 1; i < stockFromDateList.size(); i++) {
            StockFromDate stockFromDate1 = stockFromDateList.get(i - 1);
            StockFromDate stockFromDate2 = stockFromDateList.get(i);

            Double growthCalc = (stockFromDate1.getClose() - stockFromDate2.getClose()) / stockFromDate1.getClose();

            HistoryElem historyElem = new HistoryElem();
            historyElem.setDate(stockFromDate2.getTradeDate());
            historyElem.setShortName(stockFromDate2.getShortName());
            historyElem.setGrowth(growthCalc);
            historyElem.setHistory(newHistory);

            historyElems.add(historyElem);
        }
        historyElemRepositories.saveAll(historyElems);


        return mapper.historyToHistoryDto(history);
//        return null;
    }
}
