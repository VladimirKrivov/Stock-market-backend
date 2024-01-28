package stock.market.backend.app.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Stock123 {
    private String name;
    private String tradeDate;
    private double close;

    // конструктор, геттеры и сеттеры

    public static List<Double> calculateGrowthRate(List<Stock123> stocks) {
        List<Double> growthRates = new ArrayList<>();

        for (int i = 1; i < stocks.size(); i++) {
            Stock123 stock1 = stocks.get(i - 1);
            Stock123 stock2 = stocks.get(i);

            double growthRate = (stock2.getClose() - stock1.getClose()) / stock1.getClose();
            growthRates.add(growthRate);
        }

        return growthRates;
    }





    public static void main(String[] args) {
        // пример использования метода
        List<Stock123> stocks = new ArrayList<>();

        // добавьте объекты Stock в список stocks
        stocks.add(new Stock123("Роснефть", "09.01.2024", 15));
        stocks.add(new Stock123("Роснефть", "10.01.2024", 16));
        stocks.add(new Stock123("Роснефть", "11.01.2024", 18));
        stocks.add(new Stock123("Роснефть", "17.01.2024", 17));
        stocks.add(new Stock123("Роснефть", "17.01.2024", 17));
        stocks.add(new Stock123("Роснефть", "17.01.2024", 18));
        stocks.add(new Stock123("Роснефть", "17.01.2024", 10));
        stocks.add(new Stock123("Роснефть", "17.01.2024", 12));
        stocks.add(new Stock123("Роснефть", "17.01.2024", 16));
        stocks.add(new Stock123("Роснефть", "17.01.2024", 16));
        stocks.add(new Stock123("Роснефть", "17.01.2024", 19));



        List<Double> growthRates = calculateGrowthRate(stocks);

        System.out.println("Темп прироста акций по цене закрытия за каждые два дня: ");
        for (double growthRate : growthRates) {
            String result = String.format("%.2f", growthRate);
            System.out.println(result);
        }
    }
}