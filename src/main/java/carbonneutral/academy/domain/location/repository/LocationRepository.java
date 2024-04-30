package carbonneutral.academy.domain.location.repository;

import carbonneutral.academy.common.BaseEntity;
import carbonneutral.academy.domain.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer>{

    Optional<Location> findByNameAndAddressAndState (String name, String address, BaseEntity.State state);
}
