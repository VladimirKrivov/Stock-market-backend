package stock.market.backend.app.models.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HistoryDto {
    private String userName;

    private String from;
    private String till;


}
