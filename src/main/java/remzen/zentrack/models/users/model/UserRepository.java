package remzen.zentrack.models.users.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UserRepository extends JpaRepository<BeanUser, Long> {
    Optional<BeanUser> findByUsername(String username);

    boolean existsByUsername(String username);
}