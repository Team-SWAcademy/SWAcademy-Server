package carbonneutral.academy.domain.user.repository;

import carbonneutral.academy.domain.user.User;
import carbonneutral.academy.domain.user.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import static carbonneutral.academy.common.BaseEntity.*;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameAndState(String username, State state);

    boolean existsByUsernameAndSocialTypeAndState(String username, SocialType socialType, State state);

}
