package devbhdn.photoblock.repository;

import devbhdn.photoblock.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = "posts")
    Optional<User> findByUsername(String username);

    @EntityGraph(attributePaths = "posts")
    Optional<User> findById(Long id);
}
