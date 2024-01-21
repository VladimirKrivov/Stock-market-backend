package stock.market.backend.app.services;

import lombok.AllArgsConstructor;
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
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL("https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/SBERP?from=2023-11-1&till=2023-11-5");
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) !=null) {
                content.append(line + "\n");
//                if (line.contains("<row BOARDID")){
//
//                }

            }
            bufferedReader.close();

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(content);
        return String.valueOf(content);
    }
}
