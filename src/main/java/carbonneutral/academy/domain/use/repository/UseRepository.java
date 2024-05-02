package carbonneutral.academy.domain.use.repository;

import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.use.enums.UseStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UseRepository extends JpaRepository<Use, LocalDateTime> {
    List<Use> findByUserIdAndStatus(int userId, UseStatus status);
    Optional<Use> findByUserIdAndUseAtBetweenAndStatus(int userId, LocalDateTime startRange, LocalDateTime endRange, UseStatus status);
}
