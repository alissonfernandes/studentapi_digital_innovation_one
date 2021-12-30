package one.digitalinnovation.studentapi.builder;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.Size;
import lombok.Builder;
import one.digitalinnovation.studentapi.dto.request.AddressDTO;
import one.digitalinnovation.studentapi.dto.request.PhoneDTO;
import one.digitalinnovation.studentapi.dto.request.StudentDTO;
import org.hibernate.validator.constraints.br.CPF;

@Builder
public class StudentDTOBuilder {
    
    @Builder.Default
    private Long id = 1L;
    
    @Builder.Default
    @Size(min = 2, max = 100)
    private String firstName = "My first name is Test";
    
    @Builder.Default
    @Size(min = 2, max = 100)
    private String lastName = "My last name is Test";
    
    @Builder.Default
    @CPF
    private String cpf = "473.359.225-69";
    
    @Builder.Default
    private String birthDate = "15-09-2015";
    
    @Builder.Default
    private List<PhoneDTO> phones;
    
    @Builder.Default
    private List<AddressDTO> address;
    
    public StudentDTO toStudentDTO(){
        phones = new ArrayList<>();
        phones.add(PhoneDTOBuilder.builder().build().toPhoneDTO());
        
        address = new ArrayList<>();
        address.add(AddressDTOBuilder.builder().build().toAddressDTO());
        
        
        return new StudentDTO(id,
        firstName,
        lastName,
        cpf,
        birthDate,
        phones,
        address);
    }
}
