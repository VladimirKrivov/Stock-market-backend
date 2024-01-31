package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import stock.market.backend.app.models.dto.StockDto;
import stock.market.backend.app.services.impl.ApiServiceImpl;
import stock.market.backend.app.util.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

@Service
@AllArgsConstructor
@Slf4j
/**
 * Сервис с помощью которого идет обращение к мосбирже
 */
public class ApiService implements ApiServiceImpl {

    private final Parser parser;

    private final String FIND_STOCKS_URL = "https://iss.moex.com/iss/securities.json?q=";
    private final String FIND_STOCKS_FROM_TILL_URL = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/";

    /**
     Находит акцию по имени.
     @param nameStock имя акции
     @return объект StockDto, представляющий найденную акцию */
    @Override
    public StockDto findStockInApi(String nameStock) {
        StringBuffer content = new StringBuffer();
        String shareLine;

        try {
            URL url = new URL(FIND_STOCKS_URL + nameStock);
            URLConnection urlConn = url.openConnection();
            log.info("Connect for: {}, Stock name: {}", FIND_STOCKS_URL, nameStock);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("common_share")) {
                    content.append(line + "\n");
                    break;
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

    /**
     Получает историю продаж акций по коду secid и указанному промежутку времени.
     @param secId код акции
     @param from начальная дата периода
     @param till конечная дата периода
     @return список строк, представляющих историю продаж акций */
    @Override
    public ArrayList<String> findHistoryInApi(String secId, String from, String till) {
        String res = FIND_STOCKS_FROM_TILL_URL + secId + ".json?from=" + from + "&till=" + till;

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
}
