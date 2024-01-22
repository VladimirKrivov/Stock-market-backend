package stock.market.backend.app.models.json.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@Setter
public class RootJson {

    private List<StockJson> data;

}
