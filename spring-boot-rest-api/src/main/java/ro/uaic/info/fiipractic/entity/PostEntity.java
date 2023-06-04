package ro.uaic.info.fiipractic.entity;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts", schema = "public", catalog = "FIIPractic")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "message")
    private String message;
    @Basic
    @Column(name = "timestamp")
    @Generated(GenerationTime.INSERT)
    private Timestamp timestamp;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany()
    @JoinTable(name = "mentions",
        joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> mentions = new HashSet<>();

    @ManyToMany()
    @JoinTable(name = "likes",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> likes = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "post_reply",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "reply_id"))
    private Set<ReplyEntity> replies = new HashSet<>();

}
