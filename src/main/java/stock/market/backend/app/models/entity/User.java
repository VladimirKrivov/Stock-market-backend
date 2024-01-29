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
@Table(name = "uzr")
@Entity
public class User {
    @Id
    @Column(name = "uzr_id", nullable = false)
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(name = "uzr_name", nullable = false)
    private String name;


    @Column(name = "uzr_password", nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_stocks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "stocks_id")
    )
    private List<Stocks> stocks;

}
