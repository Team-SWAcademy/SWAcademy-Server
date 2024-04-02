package carbonneutral.academy.user.repository;

import carbonneutral.academy.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
