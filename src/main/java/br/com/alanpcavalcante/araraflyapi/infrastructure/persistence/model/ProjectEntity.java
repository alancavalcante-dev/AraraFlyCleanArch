package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model;

import br.com.alanpcavalcante.araraflyapi.domain.project.StateBusiness;
import br.com.alanpcavalcante.araraflyapi.domain.project.TypePrice;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_project")
@EqualsAndHashCode(of = "idProject")
@ToString
@EntityListeners(AuditingEntityListener.class)
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_project")
    private UUID idProject;

    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "type_price", nullable = false)
    private TypePrice typePrice;

    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "closing_date")
    private LocalDate closingDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private StateBusiness stateBusiness;

    @CreatedDate
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer", nullable = false)
    private UserEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_developer")
    private UserEntity developer;

}

