package com.example.samsung.hilaris;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

/**
 * Created by SAMSUNG on 2017-07-31.
 */

public class Week_CheckableLinearLayout extends LinearLayout implements Checkable{

    public Week_CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChecked(boolean checked) {
        CheckBox week_box = (CheckBox)findViewById(R.id.week_check);
        if(week_box.isChecked() != checked){
            week_box.setChecked(checked);
        }
    }

    @Override
    public boolean isChecked() {
        CheckBox week_box = (CheckBox)findViewById(R.id.week_check);
        return week_box.isChecked();
    }

    @Override
    public void toggle() {
        CheckBox week_box = (CheckBox)findViewById(R.id.week_check);
        setChecked(week_box.isChecked() ? false : true);
    }
}
