package one.digitalinnovation.studentapi.service;

// camada regra de negocio

import java.util.List;
import java.util.stream.Collectors;
import one.digitalinnovation.studentapi.dto.request.StudentDTO;
import one.digitalinnovation.studentapi.entity.Student;
import one.digitalinnovation.studentapi.mapper.StudentMapper;
import one.digitalinnovation.studentapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    
    private StudentRepository studentRepository;
    
    
    private final StudentMapper studentMapper = StudentMapper.INSTANCE;
    
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
            
    public void createStudent(StudentDTO studentDTO){
        // convert studentDTO to studentModel
        Student studentToSave = studentMapper.toModel(studentDTO);
        studentRepository.save(studentToSave);
    }
    
    public List<StudentDTO> listAll(){
        List<Student> allStudent = studentRepository.findAll();
        return allStudent.stream()
                .map(student -> studentMapper.toDTO(student))
                .collect(Collectors.toList());
    }
}
