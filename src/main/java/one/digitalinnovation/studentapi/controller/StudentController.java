package one.digitalinnovation.studentapi.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import one.digitalinnovation.studentapi.dto.request.StudentDTO;
import one.digitalinnovation.studentapi.exception.StudentNotFoundException;
import one.digitalinnovation.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

// ponto de entrata da requisicao

@RestController
@RequestMapping("/api/v1/student")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class StudentController {
    
    private StudentService studentService;
    
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
    
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws StudentNotFoundException{
        studentService.deleteById(id);
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.UPGRADE_REQUIRED)
    public void updateById(@PathVariable Long id, @RequestBody StudentDTO studentDTO) throws StudentNotFoundException{
        studentService.updateById(id, studentDTO);
    }
}
