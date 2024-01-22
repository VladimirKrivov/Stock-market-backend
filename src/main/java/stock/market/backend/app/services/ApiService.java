package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import stock.market.backend.app.services.impl.ApiServiceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Service
@AllArgsConstructor
public class ApiService implements ApiServiceImpl {

    public String testMet() {
        // Название акции, которую ищем
        String stockName = "Apple&Inc.";
        String responseString = null;

        // API Московской биржи для поиска акций
        String url = "https://iss.moex.com/iss/securities.json?q=Сбербанк";

        // Создание HttpClient
        HttpClient client = HttpClientBuilder.create().build();

        // Создание запроса методом GET
        HttpGet request = new HttpGet(url);

        try {
            // Выполнение запроса
            HttpResponse response = client.execute(request);

            // Получение тела ответа в виде строки
            responseString = EntityUtils.toString(response.getEntity());

            // Обработка полученных данных
            // ...

            // Вывод результатов поиска
            System.out.println(responseString);
            return responseString;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }
}
