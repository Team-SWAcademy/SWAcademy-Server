package carbonneutral.academy.domain.user.repository;

import carbonneutral.academy.common.BaseEntity;
import carbonneutral.academy.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndState(String username, BaseEntity.State state);
}
