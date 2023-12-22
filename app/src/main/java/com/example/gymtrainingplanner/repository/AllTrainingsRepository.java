package com.example.gymtrainingplanner.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.gymtrainingplanner.models.TrainingModel;

import java.util.ArrayList;

public class AllTrainingsRepository {
    private static AllTrainingsRepository instance;
    private ArrayList<TrainingModel> allTrainingsDataSet = new ArrayList<>();

    public static AllTrainingsRepository getInstance(){
        if(instance == null){
            instance = new AllTrainingsRepository();
        }
        return instance;
    }

    public MutableLiveData<ArrayList<TrainingModel>> getAllTrainings(){
        setAllTrainings(); //Getting data from database

        MutableLiveData<ArrayList<TrainingModel>> data = new MutableLiveData<>();
        data.setValue(new ArrayList<>(allTrainingsDataSet)); // Tworzenie nowego obiektu ArrayList
        return data;
    }

    private void setAllTrainings(){
        ArrayList<TrainingModel> trainings = new ArrayList<>();
        TrainingModel training1 = new TrainingModel("Push", 7);
        TrainingModel training2 = new TrainingModel("Pull", 6);
        TrainingModel training3 = new TrainingModel("Legs", 5);
        trainings.add(training1);
        trainings.add(training2);
        trainings.add(training3);

        allTrainingsDataSet = trainings;



    }
}
