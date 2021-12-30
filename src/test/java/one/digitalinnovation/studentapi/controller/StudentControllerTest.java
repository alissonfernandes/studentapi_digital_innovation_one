package one.digitalinnovation.studentapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import one.digitalinnovation.studentapi.builder.StudentDTOBuilder;
import one.digitalinnovation.studentapi.dto.request.StudentDTO;
import one.digitalinnovation.studentapi.exception.StudentNotFoundException;
import one.digitalinnovation.studentapi.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.doThrow;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    
    private static final String STUDENT_API_URL_PATH = "/api/v1/student";
    private static final long VALID_STUDENT_ID = 1L;
    private static final long INVALID_STUDENT_ID = 2L;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Mock
    StudentService studentService;
    
    @InjectMocks
    StudentController studentController;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(studentController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }
    
    @Test
    void whenPOSTIsCalledThenAStudentIsCreated() throws Exception {
        // given
        StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        
        //when
        when(studentService.createStudent(studentDTO)).thenReturn(studentDTO);
        
        //then
        mockMvc.perform(post(STUDENT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(studentDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(studentDTO.getLastName())))
                .andExpect(jsonPath("$.lastName", is(studentDTO.getLastName())))
                .andExpect(jsonPath("$.cpf",is(studentDTO.getCpf())))
                .andExpect(jsonPath("$.birthDate", is(studentDTO.getBirthDate())));
    }
    
    @Test
    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception{
         StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
         studentDTO.setFirstName(null);
         
         // then
         mockMvc.perform(post(STUDENT_API_URL_PATH)
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(objectMapper.writeValueAsString(studentDTO)))
                 .andExpect(status().isBadRequest());
    }
    
    @Test
    void whenGETIsCalledWithValidIdThenOkStatusIsReturned() throws StudentNotFoundException, Exception {
        // given
        StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        
        // when
        when(studentService.findById(studentDTO.getId())).thenReturn(studentDTO);
        
        // then
        mockMvc.perform(get(STUDENT_API_URL_PATH + "/" + studentDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(studentDTO.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(studentDTO.getLastName())))
                .andExpect(jsonPath("$.lastName", is(studentDTO.getLastName())))
                .andExpect(jsonPath("$.cpf",is(studentDTO.getCpf())))
                .andExpect(jsonPath("$.birthDate", is(studentDTO.getBirthDate())));
    }
    
    @Test
    void whenGETIsCalledWithInvalidIdTheNotFoundStatusIsReturned() throws StudentNotFoundException, Exception {
        // given
        StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        
        // when
        when(studentService.findById(studentDTO.getId())).thenThrow(StudentNotFoundException.class);
        
        // then
        mockMvc.perform(get(STUDENT_API_URL_PATH + "/" + studentDTO.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    
    @Test
    void whenGETListWithStudentsIsCalledThenFoundStatusIsReturned() throws StudentNotFoundException, Exception {
         // given
        StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        
        // when
        when(studentService.listAll()).thenReturn(Collections.singletonList(studentDTO));
        
        // then
        mockMvc.perform(get(STUDENT_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].firstName", is(studentDTO.getFirstName())))
                .andExpect(jsonPath("$[0].lastName", is(studentDTO.getLastName())))
                .andExpect(jsonPath("$[0].lastName", is(studentDTO.getLastName())))
                .andExpect(jsonPath("$[0].cpf",is(studentDTO.getCpf())))
                .andExpect(jsonPath("$[0].birthDate", is(studentDTO.getBirthDate())));
    }
    
    @Test
    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
        // given
        StudentDTO studentDTO = StudentDTOBuilder.builder().build().toStudentDTO();
        
        // when
        doNothing().when(studentService).deleteById(studentDTO.getId());
                
        // then
        mockMvc.perform(delete(STUDENT_API_URL_PATH + "/" + studentDTO.getId())
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    
    @Test
    void whenDELETEIsCalledWithInvalidIdThenNotFoundStatusIsReturned() throws StudentNotFoundException, Exception {
        // when
        doThrow(StudentNotFoundException.class).when(studentService).deleteById(INVALID_STUDENT_ID);
        
        // then
        mockMvc.perform(delete(STUDENT_API_URL_PATH + "/" + INVALID_STUDENT_ID)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
    
}
