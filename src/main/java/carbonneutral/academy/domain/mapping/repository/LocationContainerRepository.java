package carbonneutral.academy.domain.mapping.repository;

import carbonneutral.academy.domain.mapping.LocationContainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationContainerRepository extends JpaRepository<LocationContainer, Integer> {
}
