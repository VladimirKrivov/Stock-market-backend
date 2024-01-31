package stock.market.backend.app.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.models.dto.StockFromDateDto;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class Parser {

    /**
     Метод преобразует строку json в dto акции.
     @param content, json в виде строки.
     @return объект StockDto полученный из json */
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

    /**
     Метод преобразует список строк json в список dto торгов акции.
     @param strings, список json в виде строки.
     @return List<StockFromDateDto> полученный из json */
    public List<StockFromDateDto> parseHistory(List<String> strings) {
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

        List<StockFromDateDto> historyList = new ArrayList<>();

        for (String[] arg : arraysStr) {
            StockFromDateDto historyDto = new StockFromDateDto();

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

    /**
     Метод преобразует строку в объект LocalDate.
     @param data, дата в виде строки.
     @return LocalDate полученный из строки */
    public LocalDate parseDate(String data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(data, formatter);
    }

    /**
     Метод преобразует объект LocalDate в строку.
     @param offsetDateTime, дата в виде LocalDate.
     @return String полученный из LocalDate */
    public String formatOffsetDateTime(OffsetDateTime offsetDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return offsetDateTime.format(formatter);
    }

}
