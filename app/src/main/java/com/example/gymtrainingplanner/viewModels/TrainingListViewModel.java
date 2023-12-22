package com.example.gymtrainingplanner.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gymtrainingplanner.models.TrainingModel;
import com.example.gymtrainingplanner.repository.AllTrainingsRepository;

import java.util.ArrayList;

public class TrainingListViewModel extends ViewModel {
    private MutableLiveData<ArrayList<TrainingModel>> allTrainings;
    private AllTrainingsRepository allTrainingsRepo;

    public void init(){
        if(allTrainingsRepo != null){
            return;
        }
        allTrainingsRepo = AllTrainingsRepository.getInstance();
        allTrainings = allTrainingsRepo.getAllTrainings();

    }

    public LiveData<ArrayList<TrainingModel>> getAllTrainings() { return allTrainings; }

    public void addTraining(String name, int number){
        TrainingModel training = new TrainingModel(name, number);
        ArrayList<TrainingModel> trainings = allTrainings.getValue();
        trainings.add(training);
        allTrainings.postValue(trainings);
    }
}
