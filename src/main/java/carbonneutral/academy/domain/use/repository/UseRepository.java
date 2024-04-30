package carbonneutral.academy.domain.use.repository;

import carbonneutral.academy.domain.use.Use;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UseRepository extends JpaRepository<Use, LocalDateTime> {
    List<Use> findByUserId(int userId);
}
