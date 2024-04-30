package carbonneutral.academy.domain.mapping;

import carbonneutral.academy.domain.location.Location;
import carbonneutral.academy.domain.multi_use_container.MultiUseContainer;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "location_container")
public class LocationContainer {

    @Id
    @Column(name = "location_container_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "multi_use_container_id")
    private MultiUseContainer multiUseContainer;

}
