package com.example.gymtrainingplanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.gymtrainingplanner.models.TrainingModel;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String TRAINING_TABLE = "TRAINING_TABLE";
    public static final String COLUMN_TRAINING_NAME = "TRAINING_NAME";
    public static final String COLUMN_TRAINING_EXERCISES_NUMBER = "TRAINING_EXERCISES_NUMBER";
    public static final String COLUMN_ID = "ID";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "training.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + TRAINING_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TRAINING_NAME + " TEXT, " + COLUMN_TRAINING_EXERCISES_NUMBER + " INT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(TrainingModel trainingModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TRAINING_NAME, trainingModel.getName());
        cv.put(COLUMN_TRAINING_EXERCISES_NUMBER, trainingModel.getNumberOfExercises());

        long insert = db.insert(TRAINING_TABLE, null, cv);
        if(insert == -1){
            return false;
        }
        else{
            return true;
        }
    }
}
