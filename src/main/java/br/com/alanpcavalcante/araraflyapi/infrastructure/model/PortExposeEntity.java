package br.com.alanpcavalcante.araraflyapi.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tbl_port_expose")
@Data
public class PortExposeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_port_expose")
    private UUID idPortExpose;

    @Column(nullable = false, length = 50)
    private String port;

    @ManyToOne
    @JoinColumn(name = "id_deploy")
    private DeployEntity deploy;
}
