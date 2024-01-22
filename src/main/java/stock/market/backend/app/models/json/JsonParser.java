package stock.market.backend.app.models.json;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import stock.market.backend.app.models.json.model.RootJson;

public class JsonParser {
    public RootJson parse() {
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
            System.out.println(responseString);

        } catch (Exception e) {
            e.printStackTrace();
        }



        Gson gson = new Gson();
        gson.fromJson(responseString, RootJson.class);

        return null;
    }
}
