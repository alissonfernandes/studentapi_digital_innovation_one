package one.digitalinnovation.studentapi.service;

// camada regra de negocio

import one.digitalinnovation.studentapi.entity.Student;
import one.digitalinnovation.studentapi.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    
    private StudentRepository studentRepository;
    
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }
            
    public void createStudent(Student student){
        studentRepository.save(student);
    }
}
