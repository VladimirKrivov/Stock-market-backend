package stock.market.backend.app.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import stock.market.backend.app.models.dto.HistoryDto;
import stock.market.backend.app.models.dto.StockDto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Parser {
    public StockDto parseStock(String content) {
        StockDto stock = new StockDto();

        String input = content.toString();
        input = input.trim();
        input = input.replace("[", "");
        input = input.replace("]", "");
        input = input.replace("\"", "");

        String[] parts = input.split(",");

        for (int i = 0; i < parts.length; i++) {
            String buf = parts[i];
            buf = buf.trim();
            parts[i] = buf;
        }
        stock.setSecId(parts[1]);
        stock.setShortname(parts[2]);
        stock.setRegNumber(parts[3]);
        stock.setName(parts[4]);
        stock.setIsin(parts[5]);
        stock.setEmitEntTitle(parts[8]);

        log.info("Parsed stock dto: {}", stock);
        return stock;
    }

    public List<HistoryDto> parseHistory(List<String> strings) {
        List<String> newList = new ArrayList<>();
        List<String[]> arraysStr = new ArrayList<>();
        for (String str : strings) {
            String input = str;

            input = input.trim();
            input = input.replace("[", "");
            input = input.replace("]", "");
            input = input.replace("\"", "");

            newList.add(input);
        }

        for (String str : newList) {
            String[] parts = str.split(",");
            for (int i = 0; i < parts.length; i++) {
                String buf = parts[i];
                buf = buf.trim();
                parts[i] = buf;
            }
            arraysStr.add(parts);
        }

        List<HistoryDto> historyList = new ArrayList<>();

        for (String[] arg : arraysStr) {
            HistoryDto historyDto = new HistoryDto();

            historyDto.setTradeDate(parseDate(arg[1]));
            historyDto.setShortName(arg[2]);
            historyDto.setSecId(arg[3]);
            historyDto.setValue(Double.valueOf(arg[5]));
            historyDto.setOpen(Double.valueOf(arg[6]));
            historyDto.setLow(Double.valueOf(arg[7]));
            historyDto.setHigh(Double.valueOf(arg[8]));
            historyDto.setClose(Double.valueOf(arg[11]));
            historyDto.setVolume(Double.valueOf(arg[12]));

            historyList.add(historyDto);

        }

        return historyList;
    }


    public LocalDate parseDate(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(data, formatter);
    }

}
