package carbonneutral.academy.domain.mapping.repository;

import carbonneutral.academy.domain.mapping.LocationContainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationContainerRepository extends JpaRepository<LocationContainer, Integer> {

    List<LocationContainer> findByMultiUseContainerId(int multiUseContainerId);


}
