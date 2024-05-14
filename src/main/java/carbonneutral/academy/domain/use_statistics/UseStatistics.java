package carbonneutral.academy.domain.use_statistics;


import carbonneutral.academy.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "use_statistics")
public class UseStatistics {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false, name = "total_use_count")
    private int totalUseCount;

    @Column(nullable = false, name = "total_return_count")
    private int totalReturnCount;


    public void addTotalUseCount() {
        this.totalUseCount++;
    }

    public void addTotalReturnCount() {
        this.totalReturnCount++;
    }
}
