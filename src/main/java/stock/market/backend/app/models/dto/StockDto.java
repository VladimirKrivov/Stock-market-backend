package stock.market.backend.app.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StockDto {
    private String secId;
    private String shortname;
    private String regNumber;
    private String name;
    private String isin;
    private String emitEntTitle;

}
