package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tbl_portfolio_developer")
@Data
public class PortfolioDeveloperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_portfolio")
    private UUID idPortfolio;

    @OneToOne
    @JoinColumn(name = "id_user")
    private UserEntity developer;

    @Column(nullable = false, length = 100)
    private String presentation;

    @Column(length = 180)
    private String resume;

    @Column(length = 300)
    private String description;

    @Column(name = "picture_banner_url")
    private String pictureBanner;

    @Column(name = "is_portfolio_public")
    private Boolean isPortfolioPublic;

    @Column(name = "is_average_review_public")
    private Boolean isAverageReviewPublic;

    @Column(name = "is_feedback_public")
    private Boolean isFeedbackPublic;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tbl_portfolio_technologies", // Nova tabela de junção
            joinColumns = @JoinColumn(name = "id_portfolio"),
            inverseJoinColumns = @JoinColumn(name = "id_technology")
    )
    private Set<TechnologyEntity> technologies = new HashSet<>();
}