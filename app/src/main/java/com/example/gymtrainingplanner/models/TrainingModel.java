package com.example.gymtrainingplanner.models;

public class TrainingModel {
    private String name;
    private int numberOfExercises;

    public TrainingModel(String name, int numberOfExercises) {
        this.name = name;
        this.numberOfExercises = numberOfExercises;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfExercises() {
        return numberOfExercises;
    }

    public void setNumberOfExercises(int numberOfExercises) {
        this.numberOfExercises = numberOfExercises;
    }
}
