package one.digitalinnovation.studentapi.dto.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.studentapi.enums.UfCode;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    
    private Long id;
    
    @NotEmpty
    private String cep;
    
    @NotEmpty
    private String city;
    
    @Enumerated(EnumType.STRING)
    private UfCode uf;
    
    @NotEmpty
    private String district;
    
    @NotEmpty
    private String street;
    
    private int number;
    
    private String complement;
}
