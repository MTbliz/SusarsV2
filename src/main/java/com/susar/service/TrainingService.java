package com.susar.service;

import com.susar.model.filedb.FileDbStorageException;
import com.susar.model.training.Training;
import com.susar.model.training.TrainingNotFoundException;
import com.susar.model.training.TrainingWithMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.List;

public interface TrainingService {

    Training storeTraining(Training training) throws FileDbStorageException;

    List<TrainingWithMultipartFile> createTrainingWithMultipartFileList(MultipartFile[] files, String[] names, int[] versions, ZonedDateTime[] effectiveDate, ZonedDateTime[] stopDate);

    Iterable<Training> getTrainings();

    Training getTraining(Long id) throws TrainingNotFoundException;

    Training updateTraining(Training trainingToUpdate) throws TrainingNotFoundException;

    void deleteTraining(Long id) throws TrainingNotFoundException;
}
