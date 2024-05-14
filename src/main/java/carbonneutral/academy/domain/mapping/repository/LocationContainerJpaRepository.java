package carbonneutral.academy.domain.mapping.repository;

import carbonneutral.academy.domain.location.Location;
import carbonneutral.academy.domain.mapping.LocationContainer;
import carbonneutral.academy.domain.mapping.LocationContainerId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationContainerJpaRepository extends JpaRepository<LocationContainer, LocationContainerId> {

    List<LocationContainer> findByMultiUseContainerId(int multiUseContainerId);
    List<LocationContainer> findByLocation_Id(int locationId);

}
