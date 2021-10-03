package one.digitalinnovation.studentapi.controller;

import java.util.List;
import javax.validation.Valid;
import one.digitalinnovation.studentapi.dto.request.StudentDTO;
import one.digitalinnovation.studentapi.exception.StudentNotFoundException;
import one.digitalinnovation.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public void createStudent(@RequestBody @Valid StudentDTO studentDTO){
        studentService.createStudent(studentDTO);
    }
    
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<StudentDTO> listAll(){
        return studentService.listAll();
    }
    
    @GetMapping("/{id}")
    public StudentDTO findById(@PathVariable Long id) throws StudentNotFoundException{
        return studentService.findById(id);
    }
}
