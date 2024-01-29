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

    @Column(name = "emitent_title")
    private String emitEntTitle;



    @ManyToMany(mappedBy = "stocks")
    private List<User> users;

}