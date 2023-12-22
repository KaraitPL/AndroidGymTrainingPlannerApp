package com.example.gymtrainingplanner.views;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymtrainingplanner.DataBaseHelper;
import com.example.gymtrainingplanner.R;
import com.example.gymtrainingplanner.adapters.TrainingItemAdapter;
import com.example.gymtrainingplanner.models.TrainingModel;
import com.example.gymtrainingplanner.viewModels.TrainingListViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView trainingRecView;
    private TrainingItemAdapter trainingitemAdapter;
    private Button addNewTraining;
    private TrainingListViewModel trainingListViewModel;
    private DataBaseHelper dataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataBaseHelper = new DataBaseHelper(this);

        trainingListViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(TrainingListViewModel.class);
        trainingListViewModel.init();

        trainingListViewModel.getAllTrainings().observe(this, trainings -> {
            trainingitemAdapter.notifyDataSetChanged();
            Toast.makeText(this, "Dodany", Toast.LENGTH_SHORT).show();
        });

        initializeComponents();
        initializeTrainingRecView();
    }

    private void initializeComponents() {
        trainingRecView = (RecyclerView) findViewById(R.id.trainingRecView);
        addNewTraining = (Button) findViewById(R.id.addTrainingBtn);
        addNewTraining.setOnClickListener(this);
    }

    private void initializeTrainingRecView() {
        trainingitemAdapter = new TrainingItemAdapter(trainingListViewModel.getAllTrainings().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        trainingRecView.setLayoutManager(linearLayoutManager);
        trainingRecView.setAdapter(trainingitemAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.addTrainingBtn){
            LayoutInflater inflater = LayoutInflater.from(this);
            final View view = inflater.inflate(R.layout.fragment_enter_training_name, null);
            final EditText trainingNameEdit = (EditText) view.findViewById(R.id.trainingNameEdit);
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view)
                    .setTitle("Dodaj trening");
            builder.setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            trainingListViewModel.addTraining(trainingNameEdit.getText().toString(), 0);
                            TrainingModel trainingModel = new TrainingModel(trainingNameEdit.getText().toString(), 0);
                            boolean success = dataBaseHelper.addOne(trainingModel);
                            Toast.makeText(MainActivity.this, "Success=" + success, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cofnij", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                        }
                    });
            AlertDialog dialog = builder.create();
            builder.show();
        }

    }

}