package com.example.gymtrainingplanner.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gymtrainingplanner.R;
import com.example.gymtrainingplanner.models.TrainingModel;

import java.util.ArrayList;

public class TrainingItemAdapter extends RecyclerView.Adapter<TrainingItemAdapter.ViewHolder> {

    private ArrayList<TrainingModel> trainings = new ArrayList<>();

    public TrainingItemAdapter(ArrayList<TrainingModel> trainings){
        this.trainings = trainings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.training_item_view, parent, false);
        TrainingItemAdapter.ViewHolder holder = new TrainingItemAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.trainingName.setText(trainings.get(position).getName());
        holder.trainingExerciseNumber.setText("Ilość ćwiczeń: " + trainings.get(position).getNumberOfExercises());

    }

    @Override
    public int getItemCount() {
        return trainings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView trainingName;
        private TextView trainingExerciseNumber;
        public ViewHolder(@NonNull View itemView){
            super(itemView);

            trainingName = itemView.findViewById(R.id.trainingName);
            trainingExerciseNumber = itemView.findViewById(R.id.trainingExerciseNumber);

        }
    }
}
