package carbonneutral.academy.domain.location.repository;

import carbonneutral.academy.common.BaseEntity;
import carbonneutral.academy.domain.location.Location;
import carbonneutral.academy.domain.location.enums.LocationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationJpaRepository extends JpaRepository<Location, Integer>{

    Optional<Location> findByNameAndAddressAndState (String name, String address, BaseEntity.State state);

    List<Location> findByIdInAndLocationType(List<Integer> locationIds, LocationType locationType);
    List<Location> findByIdInAndIsReturnedAndLocationType(List<Integer> locationIds, boolean returned, LocationType locationType);


}
