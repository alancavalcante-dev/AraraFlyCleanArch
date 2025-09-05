package br.com.alanpcavalcante.araraflyapi.infrastructure.persistence.model;

import br.com.alanpcavalcante.araraflyapi.domain.technology.Technology;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tbl_technology")
@Data
public class TechnologyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private Technology name;

}
