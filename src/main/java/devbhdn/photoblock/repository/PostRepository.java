package devbhdn.photoblock.repository;

import devbhdn.photoblock.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("select p from Post p where p.user.id = :userId")
    List<Post> getAllByUserId(Long userId);
}
