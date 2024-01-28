package stock.market.backend.app.models.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ShortHistoryDto {
    private String userName;

    private String from;
    private String till;

    private List<StockDto> stocksList;
}
