package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock.market.backend.app.models.dto.HistoryDto;
import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.models.entity.History;
import stock.market.backend.app.models.entity.Stocks;
import stock.market.backend.app.repositories.HistoryRepository;
import stock.market.backend.app.repositories.StockRepositories;
import stock.market.backend.app.services.impl.ApiServiceImpl;
import stock.market.backend.app.util.Mapper;
import stock.market.backend.app.util.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ApiService implements ApiServiceImpl {

    private final StockRepositories stockRepositories;
    private final HistoryRepository historyRepository;
    private final Parser parser;
    private final Mapper mapper;

    @Override
    public StockDto findStock(String nameStock) {
        StockDto stockDto = findStockInApi(nameStock);
        Stocks stock = stockRepositories.findBySecId(stockDto.getSecId());
        StockDto newDto;
        if (stock == null) {
            return mapper.stockToStockDto(stockRepositories
                    .save(mapper.stocksDtoToStock(stockDto)));
        } else {
            newDto = mapper.stockToStockDto(stock);
        }
        return newDto;
    }

    @Override
    public Stocks findStockEntity(String nameStock) {
        StockDto stockDto = findStockInApi(nameStock);
        Stocks stock = stockRepositories.findBySecId(stockDto.getSecId());
        if (stock == null) {
            return stockRepositories
                    .save(mapper.stocksDtoToStock(stockDto));
        } else {
            return stock;
        }
    }

    @Override
    public StockDto findStockInApi(String nameStock) {
        StringBuffer content = new StringBuffer();
        String shareLine;
        String urlApi = "https://iss.moex.com/iss/securities.json?q=";


        try {
            URL url = new URL(urlApi + nameStock);
            URLConnection urlConn = url.openConnection();
            log.info("Connect for: {}, Stock name: {}", urlApi, nameStock);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("common_share")) {
                    content.append(line + "\n");
                }
            }
            bufferedReader.close();
            shareLine = String.valueOf(content);
            log.info("Common share is find. Line: {}", content);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parser.parseStock(shareLine);
    }

    @Override
    public List<HistoryDto> getHistory(String company, String from, String till) {
        LocalDate startDate = parser.parseDate(from);
        LocalDate endDate = parser.parseDate(till);
        Integer days = getDayOn(startDate, endDate);


        Stocks stocks = findStockEntity(company);
        List<HistoryDto> historyDtoList;
        List<History> histories;

        histories = historyRepository.findByTradeDateBetweenAndSecId(startDate, endDate, stocks.getSecId());

        if (histories.size() != days) {
            historyDtoList = parser.parseHistory(findHistoryInApi(stocks.getSecId(),
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

    @Override
    public ArrayList<String> findHistoryInApi(String secId, String from, String till) {
        String res = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/"
                + secId + ".json?from=" + from + "&till=" + till;

        StringBuilder content = new StringBuilder();
        ArrayList<String> strings = new ArrayList<>();

        try {
            URL url = new URL(res);
            URLConnection urlConn = url.openConnection();
            log.info("Connect Find History url: {}, SecId: {}, Date from: {}, Till: {}", res, secId, from, till);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(secId)) {
                    String column = line;
                    strings.add(line);
                }
                content.append(line + "\n");
            }
            bufferedReader.close();
            return strings;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private Integer getDayOn(LocalDate startDate, LocalDate endDate) {
        Integer workingDays = 0;
        LocalDate date = startDate;

        while (!date.isAfter(endDate)) {
            if (date.getDayOfWeek() != DayOfWeek.SATURDAY && date.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workingDays++;
            }
            date = date.plusDays(1);
        }
        return workingDays;
    }


}
