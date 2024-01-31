package stock.market.backend.app.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock_from_date_table")
@Entity
@ToString
public class StockFromDate {
    @Id
    @Column(name = "stock_frame_date_id", nullable = false)
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
