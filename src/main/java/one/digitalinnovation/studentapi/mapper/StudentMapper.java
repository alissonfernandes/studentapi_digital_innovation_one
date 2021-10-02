package one.digitalinnovation.studentapi.mapper;

import one.digitalinnovation.studentapi.dto.request.StudentDTO;
import one.digitalinnovation.studentapi.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

// conversao de objectDTO para objetModel e objectModel para objectDTO

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    
    @Mapping(target = "birthDate", source = "birthDate", dateFormat = "dd-MM-yyyy")
    Student toModel(StudentDTO studentDTO);
    
    StudentDTO toDTO(Student student);
}
