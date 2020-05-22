package com.susar.model.training;

public class TrainingNotFoundException extends Exception {
    public TrainingNotFoundException() {super(); }

    public TrainingNotFoundException(Long id){
        super(String.format("Training with id %d not found", id));
    }
}



