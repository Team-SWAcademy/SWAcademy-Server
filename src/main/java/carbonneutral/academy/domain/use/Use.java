package carbonneutral.academy.domain.use;

import carbonneutral.academy.domain.location.Location;
import carbonneutral.academy.domain.use.enums.UseStatus;
import carbonneutral.academy.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "uses")
public class Use {

    @Id
    @Column(nullable = false, updatable = false, name = "use_at")
    private LocalDateTime useAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location rentalLocation;

    @Column(name = "return_time")
    private LocalDateTime returnTime;

    @Column(nullable = false, name = "multi_use_container_id")
    private int multiUseContainerId;

    @Column(nullable = false)
    private int point;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UseStatus status;


}
