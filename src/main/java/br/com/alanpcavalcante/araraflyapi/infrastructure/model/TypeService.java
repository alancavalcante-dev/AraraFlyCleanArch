package br.com.alanpcavalcante.araraflyapi.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_type_service")
public class TypeService {

    @Id
    @Column(name = "id_type_service")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idTypeService;

    @Column
    private String service;

}
