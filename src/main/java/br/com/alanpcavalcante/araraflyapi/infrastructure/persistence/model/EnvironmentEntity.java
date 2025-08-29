package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tbl_environment")
@Data
public class EnvironmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_environment")
    private UUID idEnvironment;

    @Column(length = 50)
    private String key;

    @Column(length = 50)
    private String value;

    @ManyToOne
    @JoinColumn(name = "id_deploy")
    private DeployEntity deploy;
}
