package one.digitalinnovation.studentapi.controller;

import one.digitalinnovation.studentapi.entity.Student;
import one.digitalinnovation.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// ponto de entrata da requisicao

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    
    private StudentService studentService;
    
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
   
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student){
        studentService.createStudent(student);
    }
    
    
}
