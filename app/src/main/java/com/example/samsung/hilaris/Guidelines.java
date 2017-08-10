package com.example.samsung.hilaris;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by js960 on 2017-08-10.
 */

public class Guidelines {

    public int getNumOfguidelines() {
        return numOfguidelines;
    }

    public int numOfguidelines = 0 ;

   public  GuideLineObject[] Objects;

    Guidelines(JSONArray GuidelineArray) throws JSONException {
        this.numOfguidelines = GuidelineArray.length();
        Objects = new GuideLineObject[numOfguidelines];
        for(int i =0 ; i<numOfguidelines; i++)
        {
            Objects[i] = new GuideLineObject(GuidelineArray.getJSONObject(i));
        }
    }

}
