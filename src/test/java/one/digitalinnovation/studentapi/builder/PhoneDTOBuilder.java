package one.digitalinnovation.studentapi.builder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;
import lombok.Builder;
import one.digitalinnovation.studentapi.dto.request.PhoneDTO;
import one.digitalinnovation.studentapi.enums.PhoneType;

@Builder
public class PhoneDTOBuilder {
    @Builder.Default
    private Long id = 0L;
    
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private PhoneType type = PhoneType.HOME;
    
    @Builder.Default
    @Size(min = 13, max = 14)
    private String number = "(55)000000000";
    
    public PhoneDTO toPhoneDTO(){
        return new PhoneDTO(id, type, number);
    }
}
