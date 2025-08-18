package br.com.alanpcavalcante.araraflyapi.infrastructure.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_profile")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_profile")
    private UUID idProfile;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 13)
    private String phone;

    @Column(length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "picture_url")
    private String pictureUrl;

    @Column(nullable = false, name = "is_developer")
    private Boolean isDeveloper;

    private BigDecimal balance;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate dateCreated;

    @LastModifiedDate
    private LocalDateTime dateLastModify;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private AddressEntity address;

    @OneToOne(mappedBy = "profile")
    private UserEntity user;

}
