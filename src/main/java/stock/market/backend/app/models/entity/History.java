package stock.market.backend.app.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history_table")
@Entity
public class History {
    @Id
    @Column(name = "history_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "from_date")
    private LocalDate from;

    @Column(name = "till_date")
    private LocalDate till;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "history")
    private List<HistoryElem> historyElem;



//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
//    private Set<Repository> repositories = new HashSet<>();


}
