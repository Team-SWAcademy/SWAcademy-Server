package carbonneutral.academy.domain.point;

import carbonneutral.academy.common.BaseEntity;
import carbonneutral.academy.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "point")
public class Point {


    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    private int userId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, name = "accumulated_point")
    private int accumulatedPoint;

    @Column(nullable = false, name = "utilized_point")
    private int utilizedPoint;
}
