package com.example.samsung.hilaris;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *GuideLineObject and Prescription class parses Exercise_Routine
 */

public class Exercise_routine {
    public String Title;
    private JSON JSONroutine;
    private JSON Exercises;

    private JSONArray JsonArray;
    public Exercise_unit[] exercise_unit;
    public int unit_length;

    Exercise_routine(JSONObject routine) throws JSONException {
        JSONroutine = new JSON(routine);
        Title = JSONroutine.getTitle();
        Exercises = new JSON(JSONroutine.getExercises());
        JsonArray = Exercises.getExercise_Unit();

        unit_length = JsonArray.length();
        exercise_unit = new Exercise_unit[unit_length];
        for(int i = 0 ; i< unit_length; i++)
        {
            exercise_unit[i] = new Exercise_unit(JsonArray.getJSONObject(i));
        }
    }

    public String getExerciseUnit() {
        return exercise_unit.toString();
    }

}
