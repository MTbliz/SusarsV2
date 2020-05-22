package com.susar.controler;

import com.susar.model.filedb.FileDb;
import com.susar.model.filedb.FileDbStorageException;
import com.susar.model.training.Training;
import com.susar.model.training.TrainingNotFoundException;
import com.susar.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TrainingController {

    @Autowired
    TrainingService trainingService;

    @Autowired
    private TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }


    @PostMapping("/uploadTraining")
    public Training uploadTraining(@RequestParam("file") MultipartFile file,
                                   @RequestParam("name") String name,
                                   @RequestParam("version") int version,
                                   @RequestParam("effectiveDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime effectiveDate,
                                   @RequestParam(name = "stopDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime stopDate) throws FileDbStorageException, IOException {
        FileDb fileDb = new FileDb(name, file.getContentType(), file.getBytes());
        Training training = new Training(name, version, effectiveDate, stopDate, fileDb);
        return trainingService.storeTraining(training);
    }

    @PostMapping("/uploadMultipleTrainings")
    public List<Training> uploadMultipleTrainings(@RequestParam("files") MultipartFile[] files,
                                                  @RequestParam("names") String[] names,
                                                  @RequestParam("versions") int[] versions,
                                                  @RequestParam("effectiveDates") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime[] effectiveDate,
                                                  @RequestParam(name = "stopDates") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime[] stopDate) {

        return trainingService.createTrainingWithMultipartFileList(files, names, versions, effectiveDate, stopDate)
                .stream()
                .map(trainingWithMultipartFile -> {
                    try {
                        return uploadTraining(trainingWithMultipartFile.getMultipartFile(),
                                trainingWithMultipartFile.getName(),
                                trainingWithMultipartFile.getVersion(),
                                trainingWithMultipartFile.getEffectiveDate(),
                                trainingWithMultipartFile.getStopDate());
                    } catch (FileDbStorageException | IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/training")
    public Iterable<Training> getTrainings() throws TrainingNotFoundException {
        Iterable<Training> trainings = trainingService.getTrainings();
        return trainings;
    }


    @GetMapping("/training/{id}")
    public Training getTraining(@PathVariable Long id) throws TrainingNotFoundException {
        return trainingService.getTraining(id);
    }

    @PutMapping("/training/{id}")
    public Training updateTraining(@RequestBody Training trainingToUpdate) throws TrainingNotFoundException {
        return trainingService.updateTraining(trainingToUpdate);
    }

    @DeleteMapping("/training/{id}")
    public void deleteTraining(@PathVariable Long id) throws TrainingNotFoundException {
        trainingService.deleteTraining(id);
    }
}
