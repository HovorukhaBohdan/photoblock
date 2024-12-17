package devbhdn.photoblock.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;

@Data
@Entity
@Table(name = "following")
@SQLDelete(sql = "UPDATE following SET is_deleted = true WHERE id = ?")
@NoArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "follower_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User follower;

    @ManyToOne
    @JoinColumn(name = "following_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User following;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;
}
