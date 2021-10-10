package one.digitalinnovation.studentapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.studentapi.enums.UfCode;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String cep;
    
    @Column(nullable = false)
    private String city;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UfCode uf;
    
    @Column(nullable = false)
    private String district;
    
    @Column(nullable = false)
    private String street;
    
    @Column(nullable = false)
    private int number;
    
    @Column
    private String complement;
    
}
