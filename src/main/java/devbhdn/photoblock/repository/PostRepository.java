package devbhdn.photoblock.repository;

import devbhdn.photoblock.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.user.username = :username")
    List<Post> getAllByUsersUsername(String username);
}
