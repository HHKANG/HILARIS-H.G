package com.example.samsung.hilaris;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by js960 on 2017-08-10.
 */

public class Prescription {
    public Exercise_routine[] routine;
    public int routine_length;
    Prescription(JSONArray exercise_routine) throws JSONException {
        routine_length = exercise_routine.length();
        routine = new Exercise_routine[routine_length];
        for(int i = 0; i<routine_length; i++)
        {
            routine[i] = new Exercise_routine(exercise_routine.getJSONObject(i));
        }

    }

}
