package com.example.samsung.hilaris;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * GuideLineObjects class is one of the Guidelines.
 * GuideLineObjects class has title, description,numOfroutines attribute.
 */

public class GuideLineObject {



    public String title;
    public String description;

    public String routine[];
    private JSON json;
    private JSONArray routineArray;
    private JSON jsonObject;
    public int numOfroutines;

    GuideLineObject(JSONObject JsonObject) throws JSONException {


        json = new JSON (JsonObject);
        title = json.getTitle();
        description = json.get_Description();
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
