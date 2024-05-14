package carbonneutral.academy.domain.multi_use_container.repository;

import carbonneutral.academy.domain.multi_use_container.MultiUseContainer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MultiUseContainerJpaRepository extends JpaRepository<MultiUseContainer, Integer> {
}
