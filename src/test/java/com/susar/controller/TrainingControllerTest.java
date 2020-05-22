package com.susar.controller;

import com.susar.model.filedb.FileDb;
import com.susar.model.training.Training;
import com.susar.service.TrainingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TrainingControllerTest {

    @MockBean
    private TrainingService trainingService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldUploadTrainingTest() throws Exception {

        byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart = new MockMultipartFile("file", "orig", null, fileContent);

        when(trainingService.storeTraining(any(Training.class))).thenReturn(new Training("Test File", 1, null, null, new FileDb()));
        MvcResult result = mockMvc.perform(multipart("/uploadTraining")
                .file(filePart)
                .param("name", "Test File")
                .param("version", "1")
                .param("effectiveDate", "")
                .param("stopDate", "")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value("Test File"))
                .andExpect(jsonPath("$.version").value(1))
                .andReturn();
    }

    @Test
    void shouldUploadMultipleTrainingsTest() throws Exception {
        byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart = new MockMultipartFile("file", "orig", null, fileContent);
        MockMultipartFile filePart2 = new MockMultipartFile("file", "orig", null, fileContent);

        mockMvc.perform(multipart("/uploadMultipleTrainings")
                .file(filePart)
                .file(filePart2)
                .param("names", "Test File", "Test File2")
                .param("versions", "1", "2")
                .param("effectiveDates", "", "")
                .param("stopDates", "", "")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}