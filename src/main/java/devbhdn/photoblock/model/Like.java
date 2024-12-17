package devbhdn.photoblock.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

@Data
@Entity
@Table(name = "likes")
@SQLDelete(sql = "UPDATE likes SET is_deleted = true WHERE id = ?")
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Post post;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
