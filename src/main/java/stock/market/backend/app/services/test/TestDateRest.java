package stock.market.backend.app.services.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class TestDateRest {
    public static void main(String[] args) {


        String secId = "SBER";
        ArrayList<String> contentList;
        String res = "https://iss.moex.com/iss/history/engines/stock/markets/shares/securities/" + secId + ".json?from=2023-11-1&till=2023-11-5";
        contentList = getResponseForUrl(res, secId);


        System.out.println("Найденные строки:");
        System.out.println(contentList);

        System.out.println("Parser:");
        System.out.println(stringParser(contentList));

        System.out.println("Каждый эелмент в массиве:");
        List<String[]> arraysStr = stringParser(contentList);

        for (String[] str : arraysStr) {
            System.out.println("");
            System.out.println("------ Элемент: -------");
            for (int i = 0; i < str.length; i++) {
                System.out.println(str[i]);
            }
        }
    }


    private static List<String[]> stringParser(List<String> strings) {
        List<String> newList = new ArrayList<>();
        List<String[]> arrayString = new ArrayList<>();
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
            arrayString.add(parts);
        }



        return arrayString;
    }


    private static ArrayList<String> getResponseForUrl(String response, String secId) {
        StringBuilder content = new StringBuilder();
        ArrayList<String> strings = new ArrayList<>();

        try {
            URL url = new URL(response);
            URLConnection urlConn = url.openConnection();

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
