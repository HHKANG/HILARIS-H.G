package com.example.samsung.hilaris;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by js960 on 2017-08-10.
 */

public class Exercise_unit {

    public String id;
    public String title;
    public String week;
    public String session;
    public String phase;
    public String type;

    public String bodypart;
    public String bodypart2;
    public String equipment;
    public String description;
    public String benefit;
    public String caution;

    public String image;
    public String video;
    public String set;

    public String repetition;
    public String time;

    public String intensity;

    JSON E_Unit;

    Exercise_unit(JSONObject Unit_Object) throws JSONException {
        E_Unit = new JSON(Unit_Object);
        id = E_Unit.getPrescription_id();
        title = E_Unit.getTitle();
        week = E_Unit.getWeek();
        phase = E_Unit.getPhase();
        type = E_Unit.getType();
        bodypart = E_Unit.getBodypart();
        bodypart2 = E_Unit.getBodypart2();
        equipment = E_Unit.getEquipment();
        description = E_Unit.get_Description();
        benefit = E_Unit.getBenefit();
        caution = E_Unit.getCauiton();
        image = E_Unit.getImage();
        video = E_Unit.getVideo();
        set = E_Unit.getSet();
        repetition = E_Unit.getRepetition();
        time = E_Unit.getTime();
        intensity = E_Unit.getIntensity();
        session = E_Unit.getSession();
    }
}
