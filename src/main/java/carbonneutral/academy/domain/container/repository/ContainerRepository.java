package carbonneutral.academy.domain.container.repository;

import carbonneutral.academy.domain.container.Container;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContainerRepository extends JpaRepository<Container, Long> {
}
