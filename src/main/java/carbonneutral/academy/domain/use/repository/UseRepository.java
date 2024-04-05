package carbonneutral.academy.domain.use.repository;

import carbonneutral.academy.domain.use.Use;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UseRepository extends JpaRepository<Use, Long> {
    List<Use> findByUserIdAndIsInUse(int userId, boolean isInUse);
}
