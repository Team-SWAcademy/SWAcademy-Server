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
    @Column(nullable = false, updatable = false)
    private LocalDateTime useAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location rentalLocation;

    private LocalDateTime returnTime;

    @Column(nullable = false)
    private int multiUseContainerId;

    @Column(nullable = false)
    private int point;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UseStatus status;


}
