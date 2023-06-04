package ro.uaic.info.fiipractic.entity;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "replies", schema = "public", catalog = "FIIPractic")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private PostEntity post;

    @OneToOne()
    @JoinColumn(name = "parent")
    private PostEntity parent;
    @Basic
    @Column(name = "public_post")
    private Boolean publicPost;

}
