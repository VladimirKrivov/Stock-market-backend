package stock.market.backend.app.models.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class StockFromDateDto {
    private LocalDate tradeDate;
    private String shortName;
    private String secId;
    private Double value;
    private Double open;
    private Double low;
    private Double high;
    private Double close;
    private Double volume;

}
