package carbonneutral.academy.domain.cafe.repository;

import carbonneutral.academy.domain.cafe.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Integer> {

    Optional<Cafe> findByNameAndLocation(String name, String location);
}
