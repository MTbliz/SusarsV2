package com.susar.service;

import com.susar.model.filedb.FileDb;
import com.susar.model.filedb.FileDbStorageException;
import com.susar.model.training.Training;
import com.susar.model.training.TrainingWithMultipartFile;
import com.susar.repository.TrainingRepository;
import com.susar.service.trainingserviceimplementation.TrainingServiceImplementation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TrainingServiceTest {


    @MockBean
    private TrainingRepository trainingRepository;

    @InjectMocks
    private TrainingServiceImplementation trainingService;

    @Test
    void shouldStoreTrainingTest() throws IOException, FileDbStorageException {

        byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart = new MockMultipartFile("file", "orig", null, fileContent);
        FileDb fileDb = new FileDb("Test File", filePart.getContentType(), filePart.getBytes());
        Training training = new Training("Test File", 1, null, null, fileDb);

        when(trainingRepository.save(training)).thenReturn(training);
        assertEquals(training.getName(), trainingService.storeTraining(training).getName());
    }

    @Test
    void shouldCreateTrainingWithMultipartFileList() {

        byte[] fileContent = "bar".getBytes(StandardCharsets.UTF_8);
        MockMultipartFile filePart1 = new MockMultipartFile("file1", "orig", null, fileContent);
        MockMultipartFile filePart2 = new MockMultipartFile("file2", "orig", null, fileContent);

        MockMultipartFile[] multipartFiles = {filePart1,filePart2};
        String[] names = {"name1","name2"};
        int[] versions = {1,2};
        ZonedDateTime[] effectiveDates = {null, null};
        ZonedDateTime[] stopDates = {null, null};

        List<TrainingWithMultipartFile> result = trainingService.createTrainingWithMultipartFileList(multipartFiles, names, versions, effectiveDates, stopDates);
        assertEquals(2, result.size());
    }

}