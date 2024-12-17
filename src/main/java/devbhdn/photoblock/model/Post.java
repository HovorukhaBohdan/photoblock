package devbhdn.photoblock.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "posts")
@SQLDelete(sql = "UPDATE posts SET is_deleted = true WHERE id = ?")
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @Lob
    @Column(name = "image", nullable = false, columnDefinition = "LONGBLOB")
    private byte[] image;

    private String caption;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
