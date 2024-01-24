package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock.market.backend.app.models.dto.HistoryDto;
import stock.market.backend.app.models.entity.History;
import stock.market.backend.app.models.entity.Stocks;
import stock.market.backend.app.repositories.HistoryRepository;
import stock.market.backend.app.services.impl.HistoryServiceImpl;
import stock.market.backend.app.util.Mapper;
import stock.market.backend.app.util.Parser;
import stock.market.backend.app.util.Utils;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class HistoryService implements HistoryServiceImpl {

    private final ApiService apiService;
    private final StocksService stocksService;
    private final HistoryRepository historyRepository;
    private final Parser parser;
    private final Mapper mapper;
    private final Utils utils;


    @Override
    public List<HistoryDto> getHistory(String company, String from, String till) {
        LocalDate startDate = parser.parseDate(from);
        LocalDate endDate = parser.parseDate(till);
        Integer days = utils.getDayOn(startDate, endDate);


        Stocks stocks = stocksService.findStockEntity(company);
        List<HistoryDto> historyDtoList;
        List<History> histories;

        histories = historyRepository.findByTradeDateBetweenAndSecId(startDate, endDate, stocks.getSecId());

        if (histories.size() != days) {
            historyDtoList = parser.parseHistory(apiService.findHistoryInApi(stocks.getSecId(),
                    from, till));

            for (HistoryDto dto : historyDtoList) {
                History history = historyRepository.findByTradeDateAndSecId(dto.getTradeDate(), dto.getSecId());
                if (history == null) {
                    historyRepository.save(mapper.historyDtoToHistory(dto));
                }
            }
            histories = historyRepository.findByTradeDateBetweenAndSecId(startDate, endDate, stocks.getSecId());
        }
        log.info("Полученно историй торгов: {}, Рабочих дней за диапазон дат: {}", histories.size(), days);

        return mapper.listHistoryToHistoryDto(histories);
    }

}
