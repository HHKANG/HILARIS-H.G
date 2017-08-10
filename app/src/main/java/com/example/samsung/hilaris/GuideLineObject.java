package com.example.samsung.hilaris;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by js960 on 2017-08-10.
 */

public class GuideLineObject {



    public String routine[];

    private JSON json;
    private JSONArray routineArray;
    private JSON jsonObject;
    public int getNumOfroutines() {
        return numOfroutines;
    }

    private int numOfroutines;

    GuideLineObject(JSONObject JsonObject) throws JSONException {
        json = new JSON (JsonObject);
        routineArray = json.getRoutine();
        numOfroutines = routineArray.length();
        routine = new String[numOfroutines];
        for(int i = 0 ; i<numOfroutines; i++)
        {
            jsonObject =new JSON( routineArray.getJSONObject(i));
            routine[i] = jsonObject.getTitle();
        }
    }



}
