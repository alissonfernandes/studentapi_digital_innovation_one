package one.digitalinnovation.studentapi.service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import one.digitalinnovation.studentapi.builder.StudentDTOBuilder;
import one.digitalinnovation.studentapi.dto.request.StudentDTO;
import one.digitalinnovation.studentapi.entity.Student;
import one.digitalinnovation.studentapi.exception.StudentNotFoundException;
import one.digitalinnovation.studentapi.mapper.StudentMapper;
import one.digitalinnovation.studentapi.repository.StudentRepository;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    
    @Mock
    private StudentRepository studentRepository;
    
    StudentMapper studentMapper = StudentMapper.INSTANCE;
    
    @InjectMocks
    private StudentService studentService;
    
    
    
    @Test
    void whenStudentInformedThenItShouldBeCreated(){
        // given
        StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        Student expectedSavedStudent = studentMapper.toModel(studentDTO);
        
        // when
        when(studentRepository.save(expectedSavedStudent)).thenReturn(expectedSavedStudent);
        
        //then
        StudentDTO createdStudentDTO = studentService.createStudent(studentDTO);
        assertThat(createdStudentDTO.getFirstName(), is(equalTo(studentDTO.getFirstName())));
        assertThat(createdStudentDTO.getLastName(), is(equalTo(studentDTO.getLastName())));
        assertThat(createdStudentDTO.getCpf(), is(equalTo(studentDTO.getCpf())));
        assertThat(createdStudentDTO.getBirthDate(), is(equalTo(studentDTO.getBirthDate())));
        assertThat(createdStudentDTO.getPhones(), is(equalTo(studentDTO.getPhones())));
        assertThat(createdStudentDTO.getAddress(), is(equalTo(studentDTO.getAddress())));
    }
    
    @Test
    void whenValidStudentIdIsGivenThenReturnAStudent() throws StudentNotFoundException {
        // given
        StudentDTO expectedFoundStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        Student expectedFoundStudent = studentMapper.toModel(expectedFoundStudentDTO);
        
        // when
        when(studentRepository.findById(expectedFoundStudentDTO.getId())).thenReturn(Optional.of(expectedFoundStudent));
        
        // then
        StudentDTO foundStudentDTO = studentService.findById(expectedFoundStudentDTO.getId());
        assertThat(foundStudentDTO, is(equalTo(expectedFoundStudentDTO)));
    }
    
    @Test
    void whenNotRegisteredStudentIdIsGivenThenThrowAnException() {
        // given
        StudentDTO expectedFoundStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        
        // when
        when(studentRepository.findById(expectedFoundStudentDTO.getId())).thenReturn(Optional.empty());
        
        // then
        assertThrows(StudentNotFoundException.class, () -> studentService.findById(expectedFoundStudentDTO.getId()));
    }
    
    @Test
    void whenListStudentsIsCalledThenReturnAListOfStudents(){
        // given
        StudentDTO expectedFoundStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        Student expectedFoundStudent = studentMapper.toModel(expectedFoundStudentDTO);
        
        // when
        when(studentRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundStudent));
        
        // then
        List<StudentDTO> foundStudentsDTO = studentService.listAll();
        assertThat(foundStudentsDTO, is(not(empty())));
        assertThat(foundStudentsDTO.get(0), is(equalTo(expectedFoundStudentDTO)));
    }
    
    @Test
    void whenListStudentIsCalledThenReturnAnEmptyListOfStudents() {
        // when
        when(studentRepository.findAll()).thenReturn(Collections.EMPTY_LIST);
        
        // then
        List<StudentDTO> foundListStudentsDTO = studentService.listAll();
        assertThat(foundListStudentsDTO, is(empty()));
    }
    
    @Test
    void whenExclusionIsCalledWithValidIdThenAStudentShouldBeDeleted() throws StudentNotFoundException {
        // given
        StudentDTO expectedDeletedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        Student expectedDeletedStudent = studentMapper.toModel(expectedDeletedStudentDTO);
        
        // when
        when(studentRepository.findById(expectedDeletedStudentDTO.getId())).thenReturn(Optional.of(expectedDeletedStudent));
        doNothing().when(studentRepository).deleteById(expectedDeletedStudentDTO.getId());
        
        // then
        studentService.deleteById(expectedDeletedStudentDTO.getId());
        verify(studentRepository, times(1)).findById(expectedDeletedStudentDTO.getId());
        verify(studentRepository, times(1)).deleteById(expectedDeletedStudentDTO.getId());
    }
    
    @Test
    void whenExclusionIsCalledWithInvalidIdThenThrowAnException() throws StudentNotFoundException {
        // given
        StudentDTO expectedStudentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
     
        // when
        when(studentRepository.findById(expectedStudentDTO.getId())).thenReturn(Optional.empty());
        
        // then
        assertThrows(StudentNotFoundException.class, () -> studentService.deleteById(expectedStudentDTO.getId()));
    }
}
