package carbonneutral.academy.domain.point.repository;

import carbonneutral.academy.domain.point.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Integer> {
}
