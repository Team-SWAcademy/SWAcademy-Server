package carbonneutral.academy.domain.location;

import carbonneutral.academy.common.BaseEntity;
import carbonneutral.academy.domain.location.enums.LocationType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Getter
@Builder
@Entity
@Table(name = "location")
public class Location extends BaseEntity {

    @Id
    @Column(name = "location_id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(precision = 10, scale = 8, nullable = false)
    private BigDecimal latitude;

    @Column(precision = 11, scale = 8, nullable = false)
    private BigDecimal longitude;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20, name = "location_type")
    private LocationType locationType;

    @Column(nullable = false, name = "is_returned")
    private boolean isReturned = false;

    @Column(nullable = false, length = 200, name = "image_url")
    private String imageUrl;

}
