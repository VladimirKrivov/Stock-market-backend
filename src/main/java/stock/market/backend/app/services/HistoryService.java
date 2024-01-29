package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final HistoryElemRepositories historyElemRepositories;
    private final Parser parser;
    private final Mapper mapper;
    private final StockFromDateService stockFromDateService;
    private final UserRepositories userRepositories;

    public HistoryDto calcPricesStocks(ShortHistoryDto dto) {

        History history = new History();

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

        List<HistoryElem> allHistoryElems = new ArrayList<>();

        for (StockDto elem : stockDto) {
            List<StockFromDate> dtoDate = new ArrayList<>(stockFromDateService.getHistory(elem.getShortname(), dto.getUserName(), dto.getFrom(), dto.getTill()));
            allHistoryElems.addAll(calcGrowth(dtoDate));
        }

        for (HistoryElem elem : allHistoryElems) {
            elem.setHistory(history);
        }

        // Найти топ дня из списка акций HistoryElement
        List<HistoryElem> newHistoryElems = findProfitable(allHistoryElems);


        List<Double> res = new ArrayList<>();
        for (int i = 0; i < newHistoryElems.size(); i++) {
            double buf = newHistoryElems.get(i).getGrowth() + 1;
            res.add(buf);
        }

        Double mult = res.stream()
                .mapToDouble(a -> a)
                .reduce(1, (a, b) -> a * b);

        Double result = (mult * 365 / calendarDays);

        history.setResult(result);


        History newHistory = historyRepository.save(history);
        log.info("Сохранили Hystory: {}", history);


        historyElemRepositories.saveAll(newHistoryElems);


        newHistory.setHistoryElem(historyElemRepositories.findAllByHistory_Id(newHistory.getId()));


        return mapper.historyToHistoryDto(newHistory);
    }

    private List<HistoryElem> calcGrowth(List<StockFromDate> dtoDate) {
        List<HistoryElem> historyElems = new ArrayList<>();
        for (int i = 1; i < dtoDate.size(); i++) {
            StockFromDate stockFromDate1 = dtoDate.get(i - 1);
            StockFromDate stockFromDate2 = dtoDate.get(i);

            Double growthCalc = (stockFromDate2.getClose() - stockFromDate1.getClose()) / stockFromDate1.getClose();

            HistoryElem historyElem = new HistoryElem();
            historyElem.setDate(stockFromDate2.getTradeDate());
            historyElem.setShortName(stockFromDate2.getShortName());
            historyElem.setGrowth(growthCalc);

            historyElems.add(historyElem);
        }

        return historyElems;
    }

    private List<HistoryElem> findProfitable(List<HistoryElem> historyElems) {
        // Создаем Map, где ключом является дата, а значением - объект класса HistoryElem
        Map<LocalDate, HistoryElem> historyElemsByDate = new HashMap<>();

        // Проходимся по списку объектов класса HistoryElem
        for (int i = 0; i < historyElems.size(); i++) {
            // Получаем дату объекта
            LocalDate date = historyElems.get(i).getDate();

            // Если в Map уже есть список для данной даты, то сравниваем их growth и заменяем на объект с наибольшим
            //значением
            if (historyElemsByDate.containsKey(date)) {

                historyElemsByDate.get(date);

                if (historyElemsByDate.get(date).getGrowth() < historyElems.get(i).getGrowth()) {
                    historyElemsByDate.put(date, historyElems.get(i));
                }
            }
            // Если в Map нет списка для данной даты, то просто добавляем объект
            else {
                historyElemsByDate.put(date, historyElems.get(i));
            }
        }

        List<HistoryElem> newHistoryElems = new ArrayList<>();

        for (Map.Entry<LocalDate, HistoryElem> entry : historyElemsByDate.entrySet()) {
            newHistoryElems.add(entry.getValue());
        }

        newHistoryElems.sort(Comparator.comparing(HistoryElem::getDate));

        return newHistoryElems;


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
