package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock.market.backend.app.exeption.NotFoundException;
import stock.market.backend.app.models.dto.HistoryDto;
import stock.market.backend.app.models.dto.ShortHistoryDto;
import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.models.entity.History;
import stock.market.backend.app.models.entity.HistoryElem;
import stock.market.backend.app.models.entity.StockFromDate;
import stock.market.backend.app.repositories.HistoryElemRepositories;
import stock.market.backend.app.repositories.HistoryRepository;
import stock.market.backend.app.repositories.UserRepositories;
import stock.market.backend.app.util.Mapper;
import stock.market.backend.app.util.Parser;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
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
        log.info("Запуск метода расчета");

        History history = new History();


        List<StockFromDate> stockFromDateList = new ArrayList<>();
        List<HistoryElem> historyElems = new ArrayList<>();

        LocalDate from = parser.parseDate(dto.getFrom());
        LocalDate till = parser.parseDate(dto.getTill());
        int calendarDays = (int) ChronoUnit.DAYS.between(from, till) + 1;


        ZoneId moscowZone = ZoneId.of("Europe/Moscow");
        history.setCreate(OffsetDateTime.now(moscowZone));

        history.setFrom(from);
        history.setTill(till);

        // Найти пользователя
        history.setUser(userRepositories.findUsersByName(dto.getUserName()));


        List<StockDto> stockDto = dto.getStocksList();

        for (StockDto elem : stockDto) {
            stockFromDateList.addAll(stockFromDateService.getHistory(elem.getShortname(), dto.getUserName(), dto.getFrom(), dto.getTill()));
        }

        for (int i = 1; i < stockFromDateList.size(); i++) {
            StockFromDate stockFromDate1 = stockFromDateList.get(i - 1);
            StockFromDate stockFromDate2 = stockFromDateList.get(i);

            Double growthCalc = (stockFromDate2.getClose() - stockFromDate1.getClose()) / stockFromDate1.getClose();

            HistoryElem historyElem = new HistoryElem();
            historyElem.setDate(stockFromDate2.getTradeDate());
            historyElem.setShortName(stockFromDate2.getShortName());
            historyElem.setGrowth(growthCalc);
            historyElem.setHistory(history);

            historyElems.add(historyElem);
        }

        Double result = historyElems.get(0).getGrowth();

        for (int i = 1; i < historyElems.size(); i++) {
            result = result * historyElems.get(i).getGrowth();
        }

        result = result + 1;

        result = ((result * (365/calendarDays)) - 1) * 100;

        System.out.println("Результат");
        System.out.println(result);

        history.setResult(result);

        History newHistory = historyRepository.save(history);
        log.info("Сохранили Hystory: {}", history);

        historyElemRepositories.saveAll(historyElems);


        newHistory.setHistoryElem(historyElemRepositories.findAllByHistory_Id(newHistory.getId()));


        System.out.println(newHistory.getHistoryElem());


        return mapper.historyToHistoryDto(newHistory);
//        return null;
    }

    public List<HistoryDto> findHistoryToUser(String username) {

        List<History> entitys = historyRepository.findAllByUser(userRepositories.findUsersByName(username));
        List<HistoryDto> historyDtoList = new ArrayList<>();

        for (History elem : entitys) {
            historyDtoList.add(mapper.historyToHistoryDto(elem));
        }


        return historyDtoList;
    }
}
