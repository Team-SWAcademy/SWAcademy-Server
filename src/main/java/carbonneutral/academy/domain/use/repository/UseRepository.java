package carbonneutral.academy.domain.use.repository;

import carbonneutral.academy.domain.use.Use;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UseRepository extends JpaRepository<Use, Long> {
}
