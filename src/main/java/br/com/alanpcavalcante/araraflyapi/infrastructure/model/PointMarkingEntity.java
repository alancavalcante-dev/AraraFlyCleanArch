package br.com.alanpcavalcante.araraflyapi.infrastructure.model;

import br.com.alanpcavalcante.araraflyapi.domain.project.Project;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_appointment")
@EntityListeners(AuditingEntityListener.class)
public class PointMarkingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_point_marking")
    private UUID idPointMarking;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_container", nullable = false)
    private ProjectEntity project;

    @Column(name = "comment")
    private String comment;

    @Column(name = "comment_generated_ia", length = 10000)
    private String commentsGeneratedIA;

    @Column(name = "date_starting", nullable = false)
    private LocalDateTime dateStarting;

    @Column(name = "date_closing")
    private LocalDateTime dateClosing;

    @CreatedDate
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity developer;
}
