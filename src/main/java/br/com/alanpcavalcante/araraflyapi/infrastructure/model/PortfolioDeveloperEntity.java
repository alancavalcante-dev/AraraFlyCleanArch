package br.com.alanpcavalcante.araraflyapi.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tbl_portfolio_developer")
@Data
public class PortfolioDeveloperEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_portfolio")
    private UUID idPortfolio;

    @Column(nullable = false, length = 100)
    private String presentation;

    @Column(nullable = false, length = 180)
    private String resume;

    @OneToOne
    @JoinColumn(name = "id_user")
    private UserEntity userDeveloper;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_profile")
    private ProfileEntity profileDeveloper;
}