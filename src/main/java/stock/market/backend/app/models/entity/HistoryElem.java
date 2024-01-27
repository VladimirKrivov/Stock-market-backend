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
@Table(name = "history_elem_table")
@Entity
public class HistoryElem {
    @Id
    @Column(name = "stocks_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "short_name")
    private String shortName;

    @Column(name = "growth")
    private Double growth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="history_id")
    private History history;



//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "project_id")
//    private Project project;
//
//
//    @Column(name = "project_id", insertable = false, updatable = false)
//    private Long projectId;

}
