package stock.market.backend.app.services;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class StockSearch {
    public static void main(String[] args) {
        // Название акции, которую ищем
        String stockName = "Apple&Inc.";

        // API Московской биржи для поиска акций
        String url = "https://api.moex.com/iss/engines/stock/markets/shares/search.json?q=" + stockName;

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
