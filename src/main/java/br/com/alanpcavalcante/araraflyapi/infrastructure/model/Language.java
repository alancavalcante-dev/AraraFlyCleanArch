package br.com.alanpcavalcante.araraflyapi.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "tbl_language")
public class Language {

    @Id
    @Column(name = "id_language")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idLanguage;

    @Column
    private String language;

}
