package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.UUID;


@Entity
@Data
@Table(name = "tbl_match")
@EntityListeners(AuditingEntityListener.class)
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_match")
    private UUID idMatch;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_project", nullable = false)
    private ProjectEntity project;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_developer", nullable = false)
    private UserEntity developer;

    @Column(name = "confirm_developer")
    private Boolean confirmDeveloper;

    @Column(name = "confirm_client")
    private Boolean confirmClient = false;

    @CreatedDate
    @Column(name = "date_match")
    private LocalDate dateMatch;
}
