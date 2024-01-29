package stock.market.backend.app.models.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class HistoryDto {
    private String userName;

    private String from;
    private String till;
    private String create;
    private Integer daysCalendar;

    private String result;

    private List<HistoryElemDto> historyElemDto;
}
