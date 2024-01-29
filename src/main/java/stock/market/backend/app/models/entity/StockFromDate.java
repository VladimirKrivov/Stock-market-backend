package stock.market.backend.app.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Stock_from_date_table")
@Entity
public class StockFromDate {
    @Id
    @Column(name = "stocks_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "trade_date")
    private LocalDate tradeDate;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "secid")
    private String secId;

    @Column(name = "value")
    private Double value;

    @Column(name = "open")
    private Double open;

    @Column(name = "low")
    private Double low;

    @Column(name = "high")
    private Double high;

    @Column(name = "close")
    private Double close;

    @Column(name = "volume")
    private Double volume;

}
