package stock.market.backend.app.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stocks_table")
@Entity
public class Stocks {
    @Id
    @Column(name = "stocks_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "secid")
    private String secId;

    @Column(name = "shortname")
    private String shortname;

    @Column(name = "regnumber")
    private String regNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "isin")
    private String isin;

//    @Column(name = "is_traded")
//    private Integer isTraded;

//    @Column(name = "emitent_id")
//    private Integer emiTentId;

    @Column(name = "emitent_title")
    private String emitEntTitle;

//    @Column(name = "emitent_inn")
//    private String emitEntInn;
//
//    @Column(name = "emitent_okpo")
//    private String emitentOkpo;
//
//    @Column(name = "gosreg")
//    private String gosReg;
//
//    @Column(name = "type")
//    private String type;
//
//    @Column(name = "group")
//    private String group;
//
//    @Column(name = "primary_boardid")
//    private String primaryBoardId;
//
//    @Column(name = "marketprice_boardid")
//    private String marketPriceBoardId;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE,
            })
    @JoinTable(name = "stocks-history",
            joinColumns = @JoinColumn(name = "stocks_id"),
            inverseJoinColumns = @JoinColumn(name = "history_id")

    )
    private List<History> histories;


}
