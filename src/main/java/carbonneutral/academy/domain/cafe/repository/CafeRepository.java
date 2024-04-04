package carbonneutral.academy.domain.cafe.repository;

import carbonneutral.academy.domain.cafe.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafeRepository extends JpaRepository<Cafe, Long> {
}
