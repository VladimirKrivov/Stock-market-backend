package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock.market.backend.app.models.dto.StockFromDateDto;
import stock.market.backend.app.models.entity.StockFromDate;
import stock.market.backend.app.models.entity.Stocks;
import stock.market.backend.app.repositories.StockFromDateRepository;
import stock.market.backend.app.services.impl.StockFromDateServiceImpl;
import stock.market.backend.app.util.Mapper;
import stock.market.backend.app.util.Parser;
import stock.market.backend.app.util.Utils;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class StockFromDateService implements StockFromDateServiceImpl {

    private final ApiService apiService;
    private final StocksService stocksService;
    private final StockFromDateRepository historyRepository;
    private final Parser parser;
    private final Mapper mapper;
    private final Utils utils;


    // Получить список продаж за период времени для конкретной компании
    @Override
    public List<StockFromDate> getHistory(String company, String from, String till) {
        LocalDate startDate = parser.parseDate(from);
        LocalDate endDate = parser.parseDate(till);
        Integer days = utils.getDayOn(startDate, endDate);


        Stocks stocks = stocksService.findStockEntity(company);
        List<StockFromDateDto> historyDtoList;
        List<StockFromDate> histories;

        histories = historyRepository.findByTradeDateBetweenAndSecId(startDate, endDate, stocks.getSecId());

        if (histories.size() != days) {
            historyDtoList = parser.parseHistory(apiService.findHistoryInApi(stocks.getSecId(),
                    from, till));

            for (StockFromDateDto dto : historyDtoList) {
                StockFromDate history = historyRepository.findByTradeDateAndSecId(dto.getTradeDate(), dto.getSecId());
                if (history == null) {
                    historyRepository.save(mapper.StockFromDateDtoToStockFromDate(dto));
                }
            }
            histories = historyRepository.findByTradeDateBetweenAndSecId(startDate, endDate, stocks.getSecId());
        }
        log.info("Полученно историй торгов: {}, Рабочих дней за диапазон дат: {}", histories.size(), days);

//        return mapper.listHistoryToHistoryDto(histories);
        return histories;
    }

}
