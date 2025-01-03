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
@IdClass(LocationContainerId.class)
@Table(name = "location_container")
public class LocationContainer {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id")
    private Location location;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "multi_use_container_id")
    private MultiUseContainer multiUseContainer;

    //getLocationId
    public int getLocationId() {
        return location.getId();
    }
}
