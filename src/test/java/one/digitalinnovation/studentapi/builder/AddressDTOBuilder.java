package one.digitalinnovation.studentapi.builder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Builder;
import one.digitalinnovation.studentapi.dto.request.AddressDTO;
import one.digitalinnovation.studentapi.enums.UfCode;

@Builder
public class AddressDTOBuilder {
    
    @Builder.Default
    private Long id = 0L;
    
    @Builder.Default
    private String cep = "27370-000";
    
   @Builder.Default
    private String city = "Campo Belo";
   
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private UfCode uf = UfCode.MG;
    
    @Builder.Default
    private String district = "Bairro";
    
    @Builder.Default
    private String street = "Rua";
    
    private int number = 204;
    
    private String complement = "A2";
    
    public AddressDTO toAddressDTO(){
        return new AddressDTO(id, cep, city, uf, district, street, number, complement);
    }
}
