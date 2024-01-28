package stock.market.backend.app.models.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class StockFromDateListDto {
    private Integer days;
    private Double ratio;

    private List<StockFromDateDto> stockFromDateDtoList;
}
