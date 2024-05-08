package carbonneutral.academy.domain.use.repository;

import carbonneutral.academy.domain.use.Use;
import carbonneutral.academy.domain.use.enums.UseStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UseJpaRepository extends JpaRepository<Use, LocalDateTime> {
    List<Use> findByUserIdAndStatus(int userId, UseStatus status);
    Optional<Use> findByUserIdAndUseAtBetweenAndStatus(int userId, LocalDateTime startRange, LocalDateTime endRange, UseStatus status);

    Slice<Use> findByUserIdAndStatus(int userId, UseStatus status, Pageable pageable);

}
