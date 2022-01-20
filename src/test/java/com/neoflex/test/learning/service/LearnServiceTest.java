package com.neoflex.test.learning.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neoflex.test.learning.entity.Student;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest()
@TestPropertySource(
        locations = "classpath:application-test.properties")
@AutoConfigureMockMvc
class LearnServiceTest {

    @Autowired
    private LearnService learnService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @BeforeEach
    void setUp() {
        Student student1 = new Student()
                .setId(1L)
                .setSurname("Ivanov")
                .setDepartment("REP")
                .setName("Aleksey")
                .setSalary(2);

        Student student2 = new Student()
                .setId(2L)
                .setSurname("Markin")
                .setDepartment("SOK")
                .setName("Petr")
                .setSalary(3);

        learnService.injectStudent(student1);
        learnService.injectStudent(student2);

        List<Student> allStudents = learnService.getAllStudent();
        assertEquals(2, allStudents.size());

    }


    @Test
    void getUp() {
        List<Student> allStudents = learnService.getAllStudent();
        assertEquals(2, allStudents.size());

        LearnService mocked = mock(LearnService.class);
        when(mocked.getAllStudent()).thenReturn(allStudents);
        assertEquals(allStudents.get(0), mocked.getAllStudent().get(0));
    }

    @Test
    void whenRequestParamGet() throws Exception {
        long studId = 1L;
        String student = objectMapper.writeValueAsString(learnService.getStudent(studId));

        mockMvc.perform(MockMvcRequestBuilders.get("/get_student")
                        .queryParam("id", Long.toString(studId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        CoreMatchers.containsString(student)
                ))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void whenRequestParamPost() throws Exception {

        long studId = 1L;
        String student = objectMapper.writeValueAsString(learnService.getStudent(studId));

        mockMvc.perform(MockMvcRequestBuilders.post("/get_student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(student))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(
                        CoreMatchers.containsString(student)
                ))
                .andDo(MockMvcResultHandlers.print());
    }
}