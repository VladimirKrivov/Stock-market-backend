package stock.market.backend.app.services.test;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import stock.market.backend.app.models.json.JsonParser;
import stock.market.backend.app.models.json.model.RootJson;
import stock.market.backend.app.models.json.model.StockJson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StockSearch {

    public static void main(String[] args) throws ParseException {
        String dateStr = "2024-01-22";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateStr, formatter);
        System.out.println(date);
    }


    public static void main123(String[] args) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL("https://iss.moex.com/iss/securities.json?q=Сбербанк");
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("common_share")) {
                    content.append(line + "\n");
                }
            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Найденная строка:");
        System.out.println(content);

//        Разбить строку на части
        String result = "";
        String input = content.toString();
        input = input.trim();
        input = input.replace("[", "");
        input = input.replace("]", "");
        input = input.replace("\"", "");



        String[] parts = input.split(",");

        System.out.println("Разбитая строка:");

        for (int i = 0; i < parts.length; i++) {
            String buf = parts[i];
            buf = buf.trim();
            parts[i] = buf;
        }

        for (String part : parts) {
            System.out.println(part);
        }

        StockJson stock = new StockJson();

        stock.setId(Integer.valueOf(parts[0]));
        stock.setSecId(parts[1]);
        stock.setShortname(parts[2]);
        stock.setRegNumber(parts[3]);
        stock.setName(parts[4]);
        stock.setIsin(parts[5]);
        stock.setIsTraded(Integer.valueOf(parts[6]));
        stock.setEmiTentId(Integer.valueOf(parts[7]));
        stock.setEmitEntTitle(parts[8]);
        stock.setEmitEntInn(parts[9]);
        stock.setEmitentOkpo(parts[10]);
        stock.setGosReg(parts[11]);
        stock.setType(parts[12]);
        stock.setGroup(parts[13]);
        stock.setPrimaryBoardId(parts[14]);

        System.out.println("Получившийся объект");
        System.out.println(stock);


    }


    public static void main1(String[] args) {
        // Название акции, которую ищем
        String stockName = "Apple&Inc.";

        // API Московской биржи для поиска акций
        String url = "https://iss.moex.com/iss/securities?q=Сбербанк";

        // Создание HttpClient
        HttpClient client = HttpClientBuilder.create().build();

        // Создание запроса методом GET
        HttpGet request = new HttpGet(url);

        try {
            // Выполнение запроса
            HttpResponse response = client.execute(request);

            // Получение тела ответа в виде строки
            String responseString = EntityUtils.toString(response.getEntity());

            // Обработка полученных данных
            // ...

            // Вывод результатов поиска
            System.out.println(responseString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
