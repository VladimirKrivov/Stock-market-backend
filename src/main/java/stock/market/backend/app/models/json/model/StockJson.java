package stock.market.backend.app.models.json.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StockJson {
    private Integer id;
    private String secId;
    private String shortname;
    private String regNumber;
    private String name;
    private String isin;
    private Integer isTraded;
    private Integer emiTentId;
    private String emitEntTitle;
    private String emitEntInn;
    private String emitentOkpo;
    private String gosReg;
    private String type;
    private String group;
    private String primaryBoardId;

}
