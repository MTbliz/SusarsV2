package com.susar.service.trainingserviceimplementation;

import com.susar.model.filedb.FileDbStorageException;
import com.susar.model.training.Training;
import com.susar.model.training.TrainingNotFoundException;
import com.susar.model.training.TrainingWithMultipartFile;
import com.susar.repository.TrainingRepository;
import com.susar.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImplementation implements TrainingService {
    private TrainingRepository trainingRepository;

    @Autowired
    public TrainingServiceImplementation(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public Training storeTraining(Training training) throws FileDbStorageException {
        // Check if the file's name contains invalid characters
        if (training.getName().contains("..")) {
            throw new FileDbStorageException("Sorry! Filename contains invalid path sequence " + training.getName());
        }
        return trainingRepository.save(training);
    }

    public List<TrainingWithMultipartFile> createTrainingWithMultipartFileList(MultipartFile[] files, String[] names, int[] versions, ZonedDateTime[] effectiveDate, ZonedDateTime[] stopDate) {

        List<TrainingWithMultipartFile> trainingWithMultipartFileList = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            if (stopDate[i] == null) {
                TrainingWithMultipartFile trainingWithMultipartFile = new TrainingWithMultipartFile(files[i], names[i], versions[i], effectiveDate[i], null);
                trainingWithMultipartFileList.add(trainingWithMultipartFile);
            } else {
                TrainingWithMultipartFile trainingWithMultipartFile = new TrainingWithMultipartFile(files[i], names[i], versions[i], effectiveDate[i], stopDate[i]);
                trainingWithMultipartFileList.add(trainingWithMultipartFile);
            }
        }
        return trainingWithMultipartFileList;

    }

    public Iterable<Training> getTrainings() {
        return trainingRepository.findAll();

    }

    public Training getTraining(Long id) throws TrainingNotFoundException {
        Optional<Training> loadedTraining = trainingRepository.findById(id);
        return loadedTraining.orElseThrow(() -> new TrainingNotFoundException(id));
    }

    public Training updateTraining(Training trainingToUpdate) throws TrainingNotFoundException {
        Optional<Training> loadedTraining = trainingRepository.findById(trainingToUpdate.getId());
        if (loadedTraining.isPresent()) {
            trainingToUpdate.getFileDb().setFileName(trainingToUpdate.getName());
            return trainingRepository.save(trainingToUpdate);
        } else {
            throw new TrainingNotFoundException(trainingToUpdate.getId());
        }
    }

    public void deleteTraining(Long id) throws TrainingNotFoundException {
        Optional<Training> loadedTraining = trainingRepository.findById(id);
        if (loadedTraining.isPresent()) {
            trainingRepository.deleteById(id);
        } else {
            throw new TrainingNotFoundException(id);
        }
    }
}
