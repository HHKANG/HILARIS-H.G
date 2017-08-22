package com.example.samsung.hilaris;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * Used by Week_Day_Select
 * @paramsetChecked : checkbox set checkd.
 * @paramisChecked() :checkbox is checked.
 * @paramtoggle() : If this day was selected, toggle it.
 */

public class Day_CheckableLinearLayout extends LinearLayout implements Checkable{

    public Day_CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChecked(boolean checked) {
        CheckBox day_box = (CheckBox)findViewById(R.id.day_check);
        if(day_box.isChecked() != checked){
            day_box.setChecked(checked);
        }
    }

    @Override
    public boolean isChecked() {
        CheckBox day_box = (CheckBox)findViewById(R.id.day_check);
        return day_box.isChecked();
    }

    @Override
    public void toggle() {
        CheckBox day_box = (CheckBox)findViewById(R.id.day_check);
        setChecked(day_box.isChecked() ? false : true);
    }
}
