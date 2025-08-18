package br.com.alanpcavalcante.araraflyapi.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@ToString
@Table(name="tbl_deploy")
public class DeployEntity {

    @Id
    @Column(name = "id_deploy")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idDeploy;

    @Column(name = "surname_service", length = 100)
    private String surnameService;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_type_service")
    private TypeService typeService;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_language")
    private Language language;

    @Column(length = 100)
    private String languageVersion;

    @Column(length = 200)
    private String entrypoint;

    @OneToMany(mappedBy = "deploy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PortExposeEntity> portsExposes = new ArrayList<>();

    @OneToMany(mappedBy = "deploy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EnvironmentEntity> variableEnvironments = new ArrayList<>();

    @JsonIgnore
    @Column(name = "is_up")
    private Boolean isUp;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project", nullable = false)
    private ProjectEntity project;

}
